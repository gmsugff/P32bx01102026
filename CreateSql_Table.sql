
CREATE TABLE IF NOT EXISTS public.tasks
(
    id serial NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    done boolean DEFAULT false,
    CONSTRAINT tasks_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tasks
    OWNER to postgres;