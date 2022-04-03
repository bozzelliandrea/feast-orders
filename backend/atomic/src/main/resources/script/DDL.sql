create TABLE public.app_order (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	bar bool NULL,
	client varchar(255) NULL,
	"content" jsonb NOT NULL,
	discount int4 NULL,
	kitchen bool NULL,
	note varchar(255) NULL,
	place_setting_number int2 NOT NULL,
	plate bool NULL,
	status varchar(255) NOT NULL,
	table_number int2 NOT NULL,
	takeaway bool NULL,
	total float8 NOT NULL,
	CONSTRAINT app_order_pkey PRIMARY KEY (id)
);

create TABLE public.category (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	color varchar(255) NULL,
	description varchar(255) NULL,
	"name" varchar(255) NOT NULL,
	processing_zone varchar(255) NOT NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);

create TABLE public.error_tracking (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	message varchar(255) NULL,
	"type" varchar(255) NULL,
	CONSTRAINT error_tracking_pkey PRIMARY KEY (id)
);

create TABLE public.printerattr (
	"name" varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	CONSTRAINT printerattr_pkey PRIMARY KEY (name)
);

create TABLE public.reporttemplate (
	"name" varchar(255) NOT NULL,
	description varchar(255) NULL,
	filepath varchar(255) NOT NULL,
	CONSTRAINT reporttemplate_pkey PRIMARY KEY (name)
);

create TABLE public.roles (
	id serial4 NOT NULL,
	"name" varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

create TABLE public.users (
	id bigserial NOT NULL,
	"password" varchar(120) NULL,
	username varchar(20) NULL,
	active bool NOT NULL DEFAULT false,
	CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

create TABLE public.menu_item (
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
	stock_id int8 NULL,
	CONSTRAINT menu_item_pkey PRIMARY KEY (id)
);

create TABLE public.printercfg (
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

create TABLE public.printercfgattrs (
	attr_id varchar(255) NOT NULL,
	cfg_id int8 NOT NULL,
	value varchar(255) NULL,
	CONSTRAINT printercfgattrs_pkey PRIMARY KEY (attr_id, cfg_id),
	CONSTRAINT fk29649u3nf8qamwxvvvtrahsqp FOREIGN KEY (attr_id) REFERENCES public.printerattr("name"),
	CONSTRAINT fkm9fc6kfdw4rn5oqpqgkg9n6ke FOREIGN KEY (cfg_id) REFERENCES public.printercfg(id)
);

create TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id),
	CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id)
);

create TABLE public.stock (
	id int8 NOT NULL,
	creation_timestamp timestamp NULL,
	creation_user varchar(255) NULL,
	update_timestamp timestamp NULL,
	update_user varchar(255) NULL,
	"version" int8 NOT NULL,
	quantity int8 NOT NULL,
	CONSTRAINT stock_pkey PRIMARY KEY (id)
);

create TABLE public.category_printercfg (
	category_id int8 NOT NULL,
	printercfg_id int8 NOT NULL,
	CONSTRAINT fkd7pl092hsk0yfvvm7g0pnq30t FOREIGN KEY (printercfg_id) REFERENCES public.printercfg(id),
	CONSTRAINT fkmqb4egd393dfen67x7b1rc2f1 FOREIGN KEY (category_id) REFERENCES public.category(id)
);

create TABLE public.order_history (
	id bigserial NOT NULL,
	"content" jsonb NULL,
	"date" date NOT NULL,
	total float8 NOT NULL,
	CONSTRAINT order_history_pkey PRIMARY KEY (id)
);

create sequence IF NOT EXISTS public.app_order_gen_sq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.category_gen_sq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.err_track_gen_sq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.menu_item_gen_sq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.order_history_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.printer_cfg_gen_sq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.roles_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

create sequence IF NOT EXISTS public.users_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

create sequence public.stock_gen_sq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 1
	cache 1
	NO CYCLE;


alter table public.menu_item add CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES public.category(id);
alter table public.menu_item add CONSTRAINT fk_stock FOREIGN KEY (stock_id) REFERENCES public.stock(id);