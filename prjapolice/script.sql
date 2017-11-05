
create table apolice
(
	numero int auto_increment
		primary key,
	segurado int not null,
        uf char(2) not null,
	valorSegurado float not null,
	valorPremio float not null,
	valorDesconto float not null
)
;

create index apolice_segurado_index
	on apolice (segurado, numero)
;

create table segurado
(
	matricula int auto_increment
		primary key,
	cpf char(11) not null,
	nome varchar(50) not null,
	endereco varchar(100) not null,
	dataNascimento date not null,
	constraint segurado_cpf_uindex
		unique (cpf)
)
;

create index segurado_nome_index
	on segurado (nome)
;

alter table apolice
	add constraint fk_apolice_segurado
		foreign key (segurado) references segurado (matricula)
;

