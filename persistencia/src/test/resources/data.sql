--Datos de registro de la tabla Ciudad
INSERT INTO ciudad VALUES (1, "Armenia");
INSERT INTO ciudad VALUES (2, "Pereira");
INSERT INTO ciudad VALUES (3, "Manizales");
INSERT INTO ciudad VALUES (4, "Bogotá");
INSERT INTO ciudad VALUES (5, "Medellin");

--Datos de registro de la tabla Usuario
INSERT INTO usuario VALUES (1, "jarvinsonvalencia@gmail.com","Jarvinson Valencia", "jarvinson123", "jvalencia", 1);
INSERT INTO usuario VALUES (2, "juanmontoya@gmail.com","Juan Montoya", "juan123", "jmontoya", 2);
INSERT INTO usuario VALUES (3, "jhontamara@gmail.com","Jhon Tamara", "tamara123", "jtamara", 3);
INSERT INTO usuario VALUES (4, "danielavillegas@gmail.com","Daniela Villegas", "daniela123", "dvillegas", 1);
INSERT INTO usuario VALUES (5, "thomaslopez@gmail.com", "Thomás Lopez", "thomas123", "tlopez", 5);

--Datos de registro de la tabla usuario_num_telefono
INSERT INTO usuario_num_telefonos VALUES (1, "3007896578", "Teléfono 1" );
INSERT INTO usuario_num_telefonos VALUES (1, "3115679809", "Teléfono 2" );
INSERT INTO usuario_num_telefonos VALUES (2, "3213450987", "Teléfono" );
INSERT INTO usuario_num_telefonos VALUES (3, "3002134246", "Teléfono" );
INSERT INTO usuario_num_telefonos VALUES (4, "3186783456", "Teléfono" );




--Datos de registro de la tabla Categoria
INSERT INTO categoria VALUES (1, "Alimentos y Bebidas");
INSERT INTO categoria VALUES (2, "Bebés");
INSERT INTO categoria VALUES (3, "Cámaras");
INSERT INTO categoria VALUES (4, "Carros y Motos");
INSERT INTO categoria VALUES (5, "Tecnología");

--Datos de registro de la tabla producto
INSERT INTO producto VALUES (100, "Caja por 6 cervezas", 0,  "2022/10/09", "Six Pack Poker", 16500, 50, 1, 1);
INSERT INTO producto VALUES (101, "Whisky Deluxe por 750 ml", 10000,  "2022/10/09", "Whisky", 125000, 20, 2, 1);
INSERT INTO producto VALUES (102, "Celular Iphone 12 Pro Max 256Gb Oro", 100000,  "2022/10/09", "Iphone 12 Pro", 6000000, 30, 3, 2);
INSERT INTO producto VALUES (103, "Televisor Led 165Cm 65 Pulgada", 0,  "2022/10/09", "Tv Led 65 pulgadas", 3350000, 100, 4, 1);
INSERT INTO producto VALUES (104, "Nevecón tipo Europeo 570 Litros Bruto", 500000,  "2022/10/09", "Nevecon", 16500, 50, 5, 3);

--Datos de registro de la tabla Administrador
INSERT INTO administrador VALUES (1, "jarvinsonvalencia@gmail.com","Jarvinson Valencia", "jarvinson123");
INSERT INTO administrador VALUES (2, "juanmontoya@gmail.com","Juan Montoya", "juan123");
INSERT INTO administrador VALUES (3, "jhontamara@gmail.com","Jhon Tamara", "tamara123");
INSERT INTO administrador VALUES (4, "danielavillegas@gmail.com","Daniela Villegas", "daniela123");
INSERT INTO administrador VALUES (5, "thomaslopez@gmail.com", "Thomás Lopez", "thomas123");

-- Datos de registro de la tabla Comentario
INSERT INTO comentario VALUES (1, 4, "2022/10/09", "Buen producto", "Gracias", 102, 3);
INSERT INTO comentario VALUES (2, 5, "2022/10/09", "Excelente producto", "Gracias", 102, 2);
INSERT INTO comentario VALUES (3, 3, "2022/10/09", "No es tal cual como en las fotos", "Gracias", 104, 4);
INSERT INTO comentario VALUES (4, 1, "2022/10/09", "Aún no llega", "Gracias", 104, 5);
INSERT INTO comentario VALUES (5, 4, "2022/10/09", "Buen producto", "Gracias", 103, 1);

--Datos de registro de la tabla chat
INSERT INTO chat VALUES (200, 101, 1);
INSERT INTO chat VALUES (201, 103, 2);
INSERT INTO chat VALUES (202, 104, 3);
INSERT INTO chat VALUES (203, 101, 4);
INSERT INTO chat VALUES (204, 102, 5);
