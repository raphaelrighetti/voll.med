create table consulta(

    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,
    status varchar(20) not null,
    motivo_cancelamento varchar(20),

    primary key(id),
    constraint fk_consultas_medico_id foreign key(medico_id) references medico(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references paciente(id)

);