create table questions (id serial not null, created_at timestamp(6), updated_at timestamp (6), question varchar(500), constraint questions_pkey primary key (id));