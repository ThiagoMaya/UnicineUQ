insert into cliente values (1,"12345","pepe@mail.com","Pepe", true, "Urlfoto");
insert into cliente values (2,"esfs","mariana@mail.com","Jose", false, "Urlfoto");
insert into cliente values (3,"gdsgdsg","benzema@mail.com","Benzema", false, "Urlfoto");
insert into cliente values (4,"sdgdsg","messi@mail.com","Leonel", true, "Urlfoto");
insert into cliente values (5,"sdgfsdgds","bicho@mail.com","cristiano",true, "Urlfoto");

insert into cliente_telefonos values (1,"dfdsfds");
insert into cliente_telefonos values (1,"3121234564");
insert into cliente_telefonos values (2,"3141234564");
insert into cliente_telefonos values (2,"3001237564");
insert into cliente_telefonos values (3,"3011234564");
insert into cliente_telefonos values (4,"3021234564");
insert into cliente_telefonos values (5,"33031234564");

insert into administrador values (1,"12345","santiago@mail.com","Santiago","3002349875");
insert into administrador values (2,"12345","Mariana@mail.com","Mariana","3016549832");
insert into administrador values (3,"12345","Cristiano@mail.com","Cristiano","3001478522");
insert into administrador values (4,"12345","Leonel@mail.com","Leonel","3002347898");
insert into administrador values (5,"12345","Saul@mail.com","Saul","30023496877");

insert into administrador_teatro values (1,"12345","camila@mail.com","Camila","3002349875");
insert into administrador_teatro values (2,"12345","juliana@mail.com","Mariana","3016549832");
insert into administrador_teatro values (3,"12345","benzema@mail.com","Karim","3001478522");
insert into administrador_teatro values (4,"12345","canelo@mail.com","Canelo","3002347898");
insert into administrador_teatro values (5,"12345","Mcgregor@mail.com","Saul","30023496877");

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

insert into pelicula values (1,1,"Taxi driver", "jhon cena","sisas","urltrl");
insert into pelicula values (2,0,"Scarface", "Al pacino","sisas","urltrl");
insert into pelicula values (3,1,"Star Wars", "Leonardo dicaprio","sisas","urltrl");
insert into pelicula values (4,0,"Dragon ball ", "Goku","sisas","urltrl");
insert into pelicula values (5,1,"El padrino 3", "Al pacino","sisas","urltrl");

insert into pelicula_imagenes values (1,"https://res.cloudinary.com/thiagomaya/image/upload/v1669225082/unicine/peliculas/c58f93efe8c7bc680f60733d21438d95_hbeo1g.jpg"," ");
insert into pelicula_imagenes values (2,"https://res.cloudinary.com/thiagomaya/image/upload/v1668563745/unicine/peliculas/El_precio_del_poder-798722679-large_lcp0sl.jpg","");
insert into pelicula_imagenes values (3,"https://res.cloudinary.com/thiagomaya/image/upload/v1669225044/unicine/peliculas/CVKqHACUYAIDqpX_fzdeab.jpg","");
insert into pelicula_imagenes values (4,"https://res.cloudinary.com/thiagomaya/image/upload/v1669225267/unicine/peliculas/FM3WoXoXIAcpFrx_filljo.jpg","");
insert into pelicula_imagenes values (5,"https://res.cloudinary.com/thiagomaya/image/upload/v1668563736/unicine/peliculas/descarga_jxjjeg.jpg","");

insert into pelicula_generos values (1,"ACCION");
insert into pelicula_generos values (1,"DRAMA");
insert into pelicula_generos values (2,"ACCION");
insert into pelicula_generos values (3,"CIENCIA-FICCION");
insert into pelicula_generos values (4,"ANIMADA");
insert into pelicula_generos values (5,"ACCION");

insert into horario values (1,"Lunes","2022-11-22 00:00:00","20:00");
insert into horario values (2,"Martes","2022-11-22 00:00:00","22:00");
insert into horario values (3,"Miercoles","2022-11-22 00:00:00","17:00");
insert into horario values (4,"Jueves","2022-11-22 00:00:00","18:00");
insert into horario values (5,"Viernes","2022-11-22 00:00:00","16:00");

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

insert into compra values (1,"2022-10-25 23:11:00","",1,15000,1,null,1);
insert into compra values (2,"2022-10-25 23:11:00",null,2,15000,2,null,1);
insert into compra values (3,"2022-10-25 23:11:00",null,3,15000,3,null,1);
insert into compra values (4,"2022-10-25 23:11:00",null,4,15000,4,null,1);
insert into compra values (5,"2022-10-25 23:11:00",null,5,15000,5,null,1);

insert into entrada values(1,null,null,null,2);
insert into entrada values(2,null,null,null,3);
insert into entrada values(3,null,null,null,4);
insert into entrada values(4,null,null,null,5);
insert into entrada values(5,null,null,null,5);

insert into confiteria values(1,"Paquete 150gr","Disponible", "https://res.cloudinary.com/thiagomaya/image/upload/v1669141721/unicine/confiterias/xhdepnipsbwgoppohrkd.jpg", "Crispetas",15000  );
insert into confiteria values(1,"Envase 300ml","Disponible", "https://res.cloudinary.com/thiagomaya/image/upload/v1669141948/unicine/confiterias/igdqoy0ha0ieuovoldpm.png", "Gaseosa",4500  );
insert into confiteria values(1,"Paquete 180gr","Disponible", "https://res.cloudinary.com/thiagomaya/image/upload/v1669099671/unicine/confiterias/e0esghcg9xvws12tucrh.jpg", "Detodito",80000  );
insert into confiteria values(1,"Hot dog + salsa","Disponible", "https://res.cloudinary.com/thiagomaya/image/upload/v1669225900/unicine/confiterias/2._Perro_Caliente_1_jmbgry.jpg", "Perro Caliente",12000  );
insert into confiteria values(1,"Barra chocolatada","Disponible", "https://res.cloudinary.com/thiagomaya/image/upload/v1669099750/unicine/confiterias/sdiwnpxhtjcnaobyeqjf.png", "Barra de chocolate",6000  );


insert into compra_confiteria values(1,1000,1,1,1);
insert into compra_confiteria values(2,1000,1,2,2);
insert into compra_confiteria values(3,1000,1,3,3);
insert into compra_confiteria values(4,1000,1,3,3);
insert into compra_confiteria values(5,1000,1,3,3);

insert into cupon values(1,"Bienvenida","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(2,"Primera Compra","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(3,"Año de estadía","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(4,"mmmm","Cupon de bienvenida","2022-10-25 23:11:00",15);
insert into cupon values(5,"cumpleaños","Cupon de bienvenida","2022-10-25 23:11:00",15);

insert into cupon_cliente values (1, true, 1, 1);
insert into cupon_cliente values (2, true, 2, 2);
insert into cupon_cliente values (3, true, 3, 3);
insert into cupon_cliente values (4, true, 4, 4);
insert into cupon_cliente values (5, true, 5, 5);



