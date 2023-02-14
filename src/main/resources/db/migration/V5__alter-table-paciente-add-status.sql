alter table paciente add status tinyint;
update paciente set status = 1;
alter table paciente modify column status tinyint not null;