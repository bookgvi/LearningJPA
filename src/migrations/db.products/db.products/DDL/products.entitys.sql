create table products.entitys
(
    id_entity integer default nextval('products.create_id_entity'::regclass) not null
	constraint elements_pk
	    primary key,
    pid_element integer
	constraint fk_element_pid
	    references products.entitys
		on delete cascade,
    elementname varchar(256),
    id_project integer
	constraint fk_element_project
	    references products.projects
		on delete cascade,
    note text
);

comment on table products.entitys is 'Таблица хранения информации про элементы (сущности, являющиеся вызывающими для пунктов меню)';

alter table products.entitys owner to postgres;

