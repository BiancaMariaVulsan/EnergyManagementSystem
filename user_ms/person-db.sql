--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    address character varying(255),
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    role_id integer
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgres;

--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, address, email, first_name, last_name, password, username, role_id) FROM stdin;
552		anamigea@yahoo.com	Ana	Migea	$2a$10$sYlAn.dY5whtqbTmkDRsz.zs9uQSMdaRlYBNiiENzjNdfgHQsG48W	AnaM	2
502		biavulsan@yahoo.com	Bianca	Vulsan	$2a$10$oPibwPXq4mZjjZxRxUwlAOCSJp3Xt5yRkfes.FkzRamNFILfrG68C	BiaV	1
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
1	Admin
2	User
\.


--
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 601, true);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: person uk_fwmwi44u55bo4rvwsv0cln012; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_fwmwi44u55bo4rvwsv0cln012 UNIQUE (email);


--
-- Name: person uk_n0i6d7rc1hqkjivk494g8j2qd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_n0i6d7rc1hqkjivk494g8j2qd UNIQUE (username);


--
-- Name: person fkfqfeq5nokuewxxtb44t9lw012; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT fkfqfeq5nokuewxxtb44t9lw012 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- PostgreSQL database dump complete
--

