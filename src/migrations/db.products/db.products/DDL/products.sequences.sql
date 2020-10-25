create sequence products.create_id_project;

alter sequence products.create_id_project owner to postgres;

create sequence products.create_id_entity
    minvalue 100
    maxvalue 10000000;

alter sequence products.create_id_entity owner to postgres;

