create extension if not exists pgcrypto;
update public.usr set password = crypt(password, gen_salt('bf', 8));