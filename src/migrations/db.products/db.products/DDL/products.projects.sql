create table products.projects
(
    id_project integer default nextval('products.create_id_project'::regclass) not null
	constraint projects_pk
	    primary key,
    projectname varchar,
    note text
);

comment on table products.projects is 'Таблица хранения информации по изделиям. Возможно расширение информацией по владельцам системы, ответственным итд';

alter table products.projects owner to postgres;

