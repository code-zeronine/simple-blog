package com.hksim.simpleBlog.config.jdsl

import com.linecorp.kotlinjdsl.dsl.jpql.Jpql
import com.linecorp.kotlinjdsl.dsl.jpql.JpqlDsl
import com.linecorp.kotlinjdsl.querymodel.QueryPart
import com.linecorp.kotlinjdsl.querymodel.jpql.JpqlQueryable
import com.linecorp.kotlinjdsl.querymodel.jpql.select.SelectQuery
import com.linecorp.kotlinjdsl.render.RenderContext
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlRenderSerializer
import com.linecorp.kotlinjdsl.render.jpql.serializer.JpqlSerializer
import com.linecorp.kotlinjdsl.render.jpql.writer.JpqlWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass

@Configuration
class JdslConfig {
    @Bean
    fun customSerializer(): JpqlLimitSerializer {
        return JpqlLimitSerializer
    }
}

data class JpqlLimit<T : Any>(
    val selectQuery: QueryPart,
    val limit: Long,
    override val returnType: KClass<T>,
) : SelectQuery<T>

object JpqlLimitSerializer : JpqlSerializer<JpqlLimit<*>> {
    override fun handledType(): KClass<JpqlLimit<*>> {
        return JpqlLimit::class
    }

    override fun serialize(
        part: JpqlLimit<*>,
        writer: JpqlWriter,
        context: RenderContext,
    ) {
        val delegate = context.getValue(JpqlRenderSerializer)

        delegate.serialize(part.selectQuery, writer, context)

        writer.write(" LIMIT ${part.limit}")
    }
}

class CustomLimitJpql : Jpql() {
    companion object Constructor : JpqlDsl.Constructor<CustomLimitJpql> {
        override fun newInstance(): CustomLimitJpql = CustomLimitJpql()
    }

    inline fun <reified T : Any> JpqlQueryable<SelectQuery<T>>.limit(limit: Int): JpqlLimit<T> {
        return JpqlLimit(
            this.toQuery(),
            limit.toLong(),
            T::class,
        )
    }
}