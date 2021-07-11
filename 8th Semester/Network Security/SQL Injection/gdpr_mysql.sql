-- removed tabs to make it executable at once

create database GDPR;
use GDPR;

create table users (
	username varchar(50) primary key,
	password varchar(128),
	description varchar(120),
	password_last_changed datetime
);

delimiter $$
create trigger hash_password_insert
before insert on users
for each row begin

set @salt = substring(sha2(rand(), 256), 1, 12);
set new.password = concat(sha2(concat(sha2(new.password, 256), @salt), 256), concat(':', @salt));
set new.password_last_changed = now();
	
end $$ 
delimiter ;

delimiter $$
create trigger hash_password_update
before update on users
for each row begin

if old.password <> new.password then
set @salt = substring(sha2(rand(), 256), 1, 12);
set new.password = concat(sha2(concat(sha2(new.password, 256), @salt), 256), concat(':', @salt));
set new.password_last_changed = now();
end if;

end $$ 
delimiter ;

insert into users (username, password, description) values ("p3170010-p3170164", "team28", "database owners");
insert into users (username, password, description) values ("admin", "admin", "examiners");
update users set password = "admin2" where username = "admin";
update users set password = "admin" where username = "admin";

----------------------------------------------------------------------------------------------------------

create table logging (
	username varchar(50),
	time datetime,
	attempt boolean
);

delimiter $$
create procedure login (in un varchar(50), in pw varchar(128), out success smallint)
begin
select count(*) into @failed_attempts
from logging
where username = un and attempt = false;

if @failed_attempts < 3 then
select password into @pw_row
from users
where username = un;

set @salt = substring(@pw_row, 66, 12);
set @hashed_pw = sha2(concat(sha2(pw, 256), @salt), 256);
set @hashed_password = substring(@pw_row, 1, 64);

if @hashed_pw = @hashed_password then 
set success = 1;
insert into logging values (un, now(), true);
else
set success = 0;
insert into logging values (un, now(), false);
end if;	
else 
set success = -1;
end if;
select success;
end $$ 
delimiter ;

call login ("admin", "admin", @result);
call login ("admin", "asdfghjkl", @result);
call login ("p3170010-p3170164", "team28", @result);
call login ("admin", "asdfghjkl", @result);
call login ("admin", "asdfghjkl", @result);
call login ("admin", "admin", @result);			