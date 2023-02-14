alter table medico add status tinyint;
update medico set status = 1;
alter table medico modify column status tinyint not null;