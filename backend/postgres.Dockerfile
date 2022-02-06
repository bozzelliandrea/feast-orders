FROM postgres

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB postgres

RUN touch /docker-entrypoint-initdb.d/init.sql

RUN echo 'prepare database structure'
COPY src/main/resources/script/DDL.sql /
RUN cp /DDL.sql /docker-entrypoint-initdb.d/init.sql

RUN echo 'load init data'
COPY src/main/resources/script/script-dml.sql /
RUN cat /script-dml.sql >> /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
