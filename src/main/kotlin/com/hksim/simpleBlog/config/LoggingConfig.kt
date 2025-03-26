package com.hksim.simpleBlog.config

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import jakarta.annotation.PostConstruct
import org.hibernate.engine.jdbc.internal.FormatStyle
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.text.SimpleDateFormat
import java.util.*

@Configuration
@EnableJpaAuditing
class LoggingConfig {
    @PostConstruct
    fun setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().logMessageFormat = P6spyPrettySqlFormatter::class.java.name
    }
}

class P6spyPrettySqlFormatter : MessageFormattingStrategy {
    override fun formatMessage(
        connectionId: Int,
        now: String,
        elapsed: Long,
        category: String,
        prepared: String,
        sql: String?,
        url: String
    ): String {
        val frmSql = formatSql(category, sql)
        val currentDate = Date()
        val currentDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val retDate = currentDateFormat.format(currentDate)

        return "$retDate | OperationTime : $elapsed ms $frmSql"
    }

    private fun formatSql(category: String, sql: String?): String? {
        if (sql == null || sql.isEmpty()) return sql

        var resultSql = sql
        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.name.equals(category)) {
            val tmplSql = sql.trim { it.isWhitespace() }.lowercase()
            resultSql = if (tmplSql.startsWith("create") || tmplSql.startsWith("alter") || tmplSql.startsWith("comment")) {
                FormatStyle.DDL.formatter.format(resultSql)
            } else {
                FormatStyle.BASIC.formatter.format(resultSql)
            }
            resultSql = "|\nHeFormatSql(P6Spy sql, Hibernate format): $resultSql"
        }
        return resultSql;
    }
}