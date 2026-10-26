org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "original_urls_short_urls_id_key"
Detail: Key (short_urls_id)=(1) already exists.
at org.postgresql.core.v3.QueryExecutorImpl.receiveErrorResponse(QueryExecutorImpl.java:2993) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.core.v3.QueryExecutorImpl.processResults(QueryExecutorImpl.java:2656) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.core.v3.QueryExecutorImpl.execute(QueryExecutorImpl.java:446) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.jdbc.PgStatement.executeInternal(PgStatement.java:533) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.jdbc.PgStatement.execute(PgStatement.java:449) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.jdbc.PgPreparedStatement.executeWithFlags(PgPreparedStatement.java:197) ~[postgresql-42.7.13.jar:42.7.13]
at org.postgresql.jdbc.PgPreparedStatement.executeUpdate(PgPreparedStatement.java:158) ~[postgresql-42.7.13.jar:42.7.13]
at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeUpdate(ProxyPreparedStatement.java:61) ~[HikariCP-7.0.2.jar:na]