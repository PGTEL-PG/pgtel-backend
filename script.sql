DROP DATABASE db_pgtel;
CREATE DATABASE db_pgtel;
CREATE TABLE IF NOT EXISTS tb_unit
(
    cd_unit      varchar(255),
    nm_unit      varchar(255) NOT NULL,
    dt_create_at timestamp         NOT NULL default CURRENT_DATE,
    dt_update_at timestamp,
    dt_delete_at timestamp,
    constraint
        pk_unit
        primary key (cd_unit)
);
CREATE TABLE IF NOT EXISTS tb_product
(
    cd_product   varchar(255),
    nm_product   varchar(255)  NOT NULL,
    vl_unit      decimal(9, 2) NOT NULL,
    cd_unit      varchar(255)  NOT NULL,
    url_picture  varchar(255),
    qt_stock     int           NOT NULL default 0,
    qt_min_stock int           NOT NULL default 5,
    dt_create_at timestamp          NOT NULL default CURRENT_DATE,
    dt_update_at timestamp,
    dt_delete_at timestamp,
    constraint
        pk_product
        primary key (cd_product),
    constraint
        fk_unit
        foreign key (cd_unit) references tb_unit (cd_unit)

);



CREATE TABLE IF NOT EXISTS tb_user
(
    cd_user      varchar(255),
    nm_user      varchar(255) NOT NULL,
    cd_email     varchar(255) NOT NULL,
    cd_password  text         NOT NULL,
    dt_create_at timestamp         NOT NULL default CURRENT_DATE,
    dt_update_at timestamp,
    dt_delete_at timestamp,
    constraint
        pk_user
        primary key (cd_user)
);



CREATE TABLE IF NOT EXISTS tb_movement
(
    cd_movement  varchar(255),
    cd_product   varchar(255) NOT NULL,
    cd_unit      varchar(255) NOT NULL,
    qt_movement  int          NOT NULL,
    dt_create_at timestamp         NOT NULL default CURRENT_DATE,
    dt_update_at timestamp,
    dt_delete_at timestamp,
    constraint
        pk_movement
        primary key (cd_movement),
    constraint
        fk_movement_product
        foreign key (cd_product)
            references tb_product (cd_product),
    constraint
        fk_movement_unit
        foreign key (cd_unit)
            references tb_unit (cd_unit)
);
