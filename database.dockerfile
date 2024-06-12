FROM postgres
COPY ./src/main/resources/persistence/BaseDeDatos.sql /docker-entrypoint-initdb.d/