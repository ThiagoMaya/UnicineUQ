insert into cliente values (1,"Sdsadsa","santiagomayacc2@gmail.com", true, "Urlfoto","pepe");
insert into cliente values (2,"esfs","mariana240312@gmail.com", false, "Urlfoto","jose");
insert into cliente values (3,"gdsgdsg","benzema@mail.com", false, "Urlfoto","Benzema");
insert into cliente values (4,"sdgdsg","messi@mail.com", true, "Urlfoto","Leonel");
insert into cliente values (5,"sdgfsdgds","bicho@mail.com",true, "Urlfoto","cristiano");

insert into cliente_telefonos values (1,"dfdsfds");
insert into cliente_telefonos values (1,"3121234564");
insert into cliente_telefonos values (2,"3141234564");
insert into cliente_telefonos values (2,"3001237564");
insert into cliente_telefonos values (3,"3011234564");
insert into cliente_telefonos values (4,"3021234564");
insert into cliente_telefonos values (5,"33031234564");

insert into administrador values (1,"4545k","santiago@mail.com","Santiago","3002349875");
insert into administrador values (2,"654gy","Mariana@mail.com","Mariana","3016549832");
insert into administrador values (3,"78475f","Cristiano@mail.com","Cristiano","3001478522");
insert into administrador values (4,"96587d","Leonel@mail.com","Leonel","3002347898");
insert into administrador values (5,"a7485r","Saul@mail.com","Saul","30023496877");

insert into administrador_teatro values (1,"4545k","santiago@mail.com","Camila","3002349875");
insert into administrador_teatro values (2,"654gy","Mariana@mail.com","Mariana","3016549832");
insert into administrador_teatro values (3,"78475f","Cristiano@mail.com","Cristiano","3001478522");
insert into administrador_teatro values (4,"96587d","Leonel@mail.com","Leonel","3002347898");
insert into administrador_teatro values (5,"a7485r","Saul@mail.com","Saul","30023496877");

insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Pereira");
insert into ciudad values (3, "Medellín");
insert into ciudad values (4, "Bogotá");
insert into ciudad values (5, "Manizales");

insert into teatro values (1, "Centro comercial unicentro", "unicine armenia", "7478965",1,1);
insert into teatro values (2, "Centro comercial pereira", "unicine pereira", "7478965",2,2);
insert into teatro values (3, "Centro comercial medellin", "unicine medellin", "7478965",3,3);
insert into teatro values (4, "Centro comercial bogota", "unicine bogota", "7478965",4,4);
insert into teatro values (5, "Centro comercial manizales", "unicine manizales", "7478965",5,5);

insert into pelicula values (1,1,"TERROR","Taxi driver", "jhon cena","sisas","urlimg","urltrl");
insert into pelicula values (2,0,"ACCION","Scarface", "Al pacino","sisas","urlimg","urltrl");
insert into pelicula values (3,1,"TERROR","Star Wars", "Leonardo dicaprio","sisas","urlimg","urltrl");
insert into pelicula values (4,0,"COMEDIA","Dragon ball ", "Goku","sisas","urlimg","urltrl");
insert into pelicula values (5,1,"ACCION","El padrino 3", "Al pacino","sisas","urlimg","urltrl");

insert into horario values (1,"2022-10-25 23:11:00","1999-10-12 01:10:00","1999-10-02 02:10:00");
insert into horario values (2,"2022-12-10 23:11:00","1999-10-12 01:10:00","1999-10-02 02:10:00");
insert into horario values (3,"2022-10-11 23:11:00","1999-10-12 01:10:00","1999-10-02 02:10:00");
insert into horario values (4,"2022-11-11 23:11:00","1999-10-12 01:10:00","1999-10-02 02:10:00");
insert into horario values (5,"2022-11-11 23:11:00","1999-10-12 01:10:00","1999-10-02 02:10:00");


insert into distribucion_sillas values(1,15,"Esquema 2", 20, 300);
insert into distribucion_sillas values(2,10,"Esquema 1", 10, 100);
insert into distribucion_sillas values(3,20,"Esquema 2", 15, 300);
insert into distribucion_sillas values(4,15,"Esquema 3", 25, 375);
insert into distribucion_sillas values(5,25,"Esquema 2", 10, 250);

insert into sala values (1,"Sala 1",1,1);
insert into sala values (2,"Sala 2",2,2);
insert into sala values (3,"Sala 3",3,3);
insert into sala values (4,"Sala XD",4,4);
insert into sala values (5,"Sala 5",5,5);

insert into funcion values (1,10.000,1,1,1);
insert into funcion values (2,8.000,2,2,2);
insert into funcion values (3,17.000,3,3,3);
insert into funcion values (4,20.000,4,4,4);
insert into funcion values (5,10.000,5,5,5);

insert into compra values (1,"2022-10-25 23:11:00",null,1,15000,1,null,1);
insert into compra values (2,"2022-10-25 23:11:00",null,2,15000,2,null,1);
insert into compra values (3,"2022-10-25 23:11:00",null,3,15000,3,null,1);
insert into compra values (4,"2022-10-25 23:11:00",null,4,15000,4,null,1);
insert into compra values (5,"2022-10-25 23:11:00",null,5,15000,5,null,1);


insert into confiteria values(1,"fritos","disponible","papitas",10000.0);
insert into confiteria values(2,"fritos","disponible","doritos",10000.0);
insert into confiteria values(3,"Bebidas","disponible","gaseosa",10000.0);
insert into confiteria values(4,"fritos","disponible","detodito",10000.0);
insert into confiteria values(5,"Comidas rapidas","disponible","hamburguesa",10000.0);

insert into compra_confiteria values(1,1000,1,1,1);
insert into compra_confiteria values(2,1000,1,2,2);
insert into compra_confiteria values(3,1000,1,3,3);
insert into compra_confiteria values(4,1000,1,3,3);
insert into compra_confiteria values(5,1000,1,3,3);

insert into cupon values(1,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(2,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(3,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(4,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(5,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);

insert into cupon_cliente values (1, true, 1, 1);
insert into cupon_cliente values (2, true, 1, 2);
insert into cupon_cliente values (3, true, 1, 3);
insert into cupon_cliente values (4, true, 1, 4);
insert into cupon_cliente values (5, true, 1, 5);


 