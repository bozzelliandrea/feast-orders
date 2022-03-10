FROM postgres

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB postgres

RUN touch /docker-entrypoint-initdb.d/init.sql

RUN echo 'prepare database structure'
COPY atomic/src/main/resources/script/DDL.sql /
RUN cp /DDL.sql /docker-entrypoint-initdb.d/init.sql

RUN echo 'load init data'
COPY atomic/src/main/resources/script/DML.sql /
RUN cat /DML.sql >> /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
