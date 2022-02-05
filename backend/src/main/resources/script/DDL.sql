-- public.app_order definition

-- Drop table

-- DROP TABLE public.app_order;

CREATE TABLE public.app_order (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	cashier varchar(255) NULL,
	client varchar(255) NOT NULL,
	note varchar(255) NULL,
	place_setting_number int8 NOT NULL,
	table_number int8 NOT NULL,
	takeaway bool NULL,
	total float4 NOT NULL,
	CONSTRAINT app_order_pkey PRIMARY KEY (id)
);


-- public.category definition

-- Drop table

-- DROP TABLE public.category;

CREATE TABLE public.category (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	color varchar(255) NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);


-- public.error_tracking definition

-- Drop table

-- DROP TABLE public.error_tracking;

CREATE TABLE public.error_tracking (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	message varchar(255) NULL,
	"type" varchar(255) NULL,
	CONSTRAINT error_tracking_pkey PRIMARY KEY (id)
);


-- public.order_new definition

-- Drop table

-- DROP TABLE public.order_new;

CREATE TABLE public.order_new (
	id bigserial NOT NULL,
	"content" jsonb NULL,
	CONSTRAINT order_new_pkey PRIMARY KEY (id)
);


-- public.printerattr definition

-- Drop table

-- DROP TABLE public.printerattr;

CREATE TABLE public.printerattr (
	"name" varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	CONSTRAINT printerattr_pkey PRIMARY KEY (name)
);


-- public.reporttemplate definition

-- Drop table

-- DROP TABLE public.reporttemplate;

CREATE TABLE public.reporttemplate (
	"name" varchar(255) NOT NULL,
	description varchar(255) NULL,
	filepath varchar(255) NOT NULL,
	CONSTRAINT reporttemplate_pkey PRIMARY KEY (name)
);


-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id serial4 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	email varchar(50) NULL,
	"password" varchar(120) NULL,
	username varchar(20) NULL,
	CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- public.menu_item definition

-- Drop table

-- DROP TABLE public.menu_item;

CREATE TABLE public.menu_item (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	color varchar(255) NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	price float4 NULL,
	category_id int8 NOT NULL,
	CONSTRAINT menu_item_pkey PRIMARY KEY (id),
	CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES public.category(id)
);


-- public.orderitem definition

-- Drop table

-- DROP TABLE public.orderitem;

CREATE TABLE public.orderitem (
	menu_item_id int8 NOT NULL,
	order_id int8 NOT NULL,
	menu_item_cat_id int8 NULL,
	menu_item_name varchar(255) NOT NULL,
	menu_item_price float4 NOT NULL,
	note varchar(255) NULL,
	quantity int8 NOT NULL,
	total_price float4 NOT NULL,
	CONSTRAINT orderitem_pkey PRIMARY KEY (menu_item_id, order_id),
	CONSTRAINT fkndnur7pxi4br02k4fckatnfur FOREIGN KEY (order_id) REFERENCES public.app_order(id)
);


-- public.printercfg definition

-- Drop table

-- DROP TABLE public.printercfg;

CREATE TABLE public.printercfg (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	printer_name varchar(255) NULL,
	report_template_name varchar(255) NULL,
	CONSTRAINT printercfg_pkey PRIMARY KEY (id),
	CONSTRAINT fksb6cq86195w6xyibxafhshkth FOREIGN KEY (report_template_name) REFERENCES public.reporttemplate("name")
);


-- public.printercfgattrs definition

-- Drop table

-- DROP TABLE public.printercfgattrs;

CREATE TABLE public.printercfgattrs (
	attr_id varchar(255) NOT NULL,
	cfg_id int8 NOT NULL,
	value varchar(255) NULL,
	CONSTRAINT printercfgattrs_pkey PRIMARY KEY (attr_id, cfg_id),
	CONSTRAINT fk29649u3nf8qamwxvvvtrahsqp FOREIGN KEY (attr_id) REFERENCES public.printerattr("name"),
	CONSTRAINT fkm9fc6kfdw4rn5oqpqgkg9n6ke FOREIGN KEY (cfg_id) REFERENCES public.printercfg(id)
);


-- public.user_roles definition

-- Drop table

-- DROP TABLE public.user_roles;

CREATE TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id),
	CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id)
);


-- public.category_printercfg definition

-- Drop table

-- DROP TABLE public.category_printercfg;

CREATE TABLE public.category_printercfg (
	category_id int8 NOT NULL,
	printercfg_id int8 NOT NULL,
	CONSTRAINT fkd7pl092hsk0yfvvm7g0pnq30t FOREIGN KEY (printercfg_id) REFERENCES public.printercfg(id),
	CONSTRAINT fkmqb4egd393dfen67x7b1rc2f1 FOREIGN KEY (category_id) REFERENCES public.category(id)
);