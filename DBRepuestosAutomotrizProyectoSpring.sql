drop database if exists DBRepuestosAutomotriz_in5cm;
create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
	id_proveedor int auto_increment not null,
	nombre_proveedor varchar(60) not null,
	telefono_proveedor int not null,
	direccion varchar(100) not null,
	email_proveedor varchar(100) not null,
	primary key (id_proveedor)
);

create table Empleados(
	id_empleado int auto_increment not null,
	nombre_empleado varchar(60) not null,
	apellido_empleado varchar(60) not null,
	puesto_empleado varchar(20) null,
	email_empleado varchar(100) not null,
	primary key (id_empleado)
);

create table Repuestos(
	id_repuesto int auto_increment not null,
	nombre_repuesto varchar(60) not null,
	categoria_repuesto varchar(60) not null,
	precio_compra double not null,
	precio_venta double not null,
	id_proveedor int not null,
	primary key (id_repuesto),
	constraint FK_repuesto_proveedor foreign key (id_proveedor)
	references Proveedores(id_proveedor) on delete cascade
);

create table Ventas(
	id_venta int auto_increment not null,
	fecha_venta date not null,
	cantidad int not null,
	total double not null,
	id_empleado int not null,
	id_repuesto int not null,
	primary key (id_venta),
	constraint FK_ventas_empleado foreign key (id_empleado)
	references Empleados(id_empleado) on delete cascade,
	constraint FK_ventas_repuestos foreign key (id_repuesto)
	references Repuestos(id_repuesto) on delete cascade
);

delimiter $$

create procedure sp_ListarProveedores()
begin
	select *
    from Proveedores
    order by id_proveedor;
end$$

delimiter ;

delimiter $$

create procedure sp_AgregarProveedor(
	in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
)
begin
	insert into Proveedores(nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
    values(p_nombre, p_telefono, p_direccion, p_email);
end$$

delimiter ;

delimiter $$

create procedure sp_ActualizarProveedor(
    in p_id int,
    in p_nombre varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_email varchar(100)
)
begin
    update Proveedores
    set nombre_proveedor = p_nombre,
        telefono_proveedor = p_telefono,
        direccion = p_direccion,
        email_proveedor = p_email
    where id_proveedor = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_EliminarProveedor(in p_id int)
begin
    delete from Proveedores
    where id_proveedor = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_BuscarProveedorPorId(
    in p_id int
)
begin
    select *
    from Proveedores
    where id_proveedor = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_ListarEmpleados()
begin
    select *
    from Empleados
    order by id_empleado;
end$$

delimiter ;

delimiter $$

create procedure sp_AgregarEmpleado(
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
    insert into Empleados(nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
    values(p_nombre, p_apellido, p_puesto, p_email);
end$$

delimiter ;

delimiter $$

create procedure sp_ActualizarEmpleado(
    in p_id int,
    in p_nombre varchar(60),
    in p_apellido varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
    update Empleados
    set nombre_empleado = p_nombre,
        apellido_empleado = p_apellido,
        puesto_empleado = p_puesto,
        email_empleado = p_email
    where id_empleado = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_EliminarEmpleado(in p_id int)
begin
    delete from Empleados
    where id_empleado = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_BuscarEmpleadoPorId(
    in p_id int
)
begin
    select *
    from Empleados
    where id_empleado = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_ListarRepuestos()
begin
    select *
    from Repuestos
    order by id_repuesto;
end$$

delimiter ;

delimiter $$

create procedure sp_AgregarRepuesto(
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
)
begin
    insert into Repuestos(nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
    values(p_nombre, p_categoria, p_precio_compra, p_precio_venta, p_id_proveedor);
end$$

delimiter ;

delimiter $$

create procedure sp_ActualizarRepuesto(
    in p_id int,
    in p_nombre varchar(60),
    in p_categoria varchar(60),
    in p_precio_compra double,
    in p_precio_venta double,
    in p_id_proveedor int
)
begin
    update Repuestos
    set nombre_repuesto = p_nombre,
        categoria_repuesto = p_categoria,
        precio_compra = p_precio_compra,
        precio_venta = p_precio_venta,
        id_proveedor = p_id_proveedor
    where id_repuesto = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_EliminarRepuesto(in p_id int)
begin
    delete from Repuestos
    where id_repuesto = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_BuscarRepuestoPorId(
    in p_id int
)
begin
    select *
    from Repuestos
    where id_repuesto = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_ListarVentas()
begin
    select *
    from Ventas
    order by id_venta;
end$$

delimiter ;

delimiter $$

create procedure sp_AgregarVenta(
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
)
begin
    insert into Ventas(fecha_venta, cantidad, total, id_empleado, id_repuesto)
    values(p_fecha, p_cantidad, p_total, p_id_empleado, p_id_repuesto);
end$$

delimiter ;

delimiter $$

create procedure sp_ActualizarVenta(
    in p_id int,
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
)
begin
    update Ventas
    set fecha_venta = p_fecha,
        cantidad = p_cantidad,
        total = p_total,
        id_empleado = p_id_empleado,
        id_repuesto = p_id_repuesto
    where id_venta = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_EliminarVenta(in p_id int)
begin
    delete from Ventas
    where id_venta = p_id;
end$$

delimiter ;

delimiter $$

create procedure sp_BuscarVentaPorId(
    in p_id int
)
begin
    select *
    from Ventas
    where id_venta = p_id;
end$$

delimiter ;

-- Registros --

call sp_AgregarProveedor('AutoPartes Morales', 22334455, 'Zona 1, Ciudad de Guatemala', 'contacto@autopartesmorales.com');
call sp_AgregarProveedor('Repuestos El Motor', 23456789, 'Zona 5, Ciudad de Guatemala', 'ventas@elmotor.com');
call sp_AgregarProveedor('Distribuidora La 20', 24567890, 'Zona 9, Ciudad de Guatemala', 'info@la20.com');
call sp_AgregarProveedor('Importadora Japonesa', 25678901, 'Zona 11, Ciudad de Guatemala', 'ventas@importadorajp.com');
call sp_AgregarProveedor('Repuestos Express', 26789012, 'Mixco, Guatemala', 'soporte@repuestosexpress.com');
call sp_AgregarProveedor('AutoZone GT', 27890123, 'Villa Nueva, Guatemala', 'gt@autozone.com');
call sp_AgregarProveedor('Repuestos Santa Fe', 28901234, 'Zona 7, Ciudad de Guatemala', 'santafe@repuestos.com');
call sp_AgregarProveedor('Motores y Más', 29012345, 'Amatitlán, Guatemala', 'contacto@motoresymas.com');
call sp_AgregarProveedor('Parts Center', 30123456, 'Zona 10, Ciudad de Guatemala', 'ventas@partscenter.com');
call sp_AgregarProveedor('AutoRepuestos del Sur', 31234567, 'Escuintla, Guatemala', 'info@autorepuestosur.com');

call sp_AgregarEmpleado('Carlos', 'Pérez', 'Vendedor', 'carlos.perez@empresa.com');
call sp_AgregarEmpleado('Ana', 'López', 'Cajera', 'ana.lopez@empresa.com');
call sp_AgregarEmpleado('Luis', 'Martínez', 'Administrador', 'luis.martinez@empresa.com');
call sp_AgregarEmpleado('María', 'Gómez', 'Vendedor', 'maria.gomez@empresa.com');
call sp_AgregarEmpleado('Jorge', 'Ramírez', 'Bodega', 'jorge.ramirez@empresa.com');
call sp_AgregarEmpleado('Sofía', 'Hernández', 'Cajera', 'sofia.hernandez@empresa.com');
call sp_AgregarEmpleado('Pedro', 'Castillo', 'Vendedor', 'pedro.castillo@empresa.com');
call sp_AgregarEmpleado('Daniela', 'Ruiz', 'Bodega', 'daniela.ruiz@empresa.com');
call sp_AgregarEmpleado('Miguel', 'Flores', 'Supervisor', 'miguel.flores@empresa.com');
call sp_AgregarEmpleado('Paola', 'Mendoza', 'Vendedor', 'paola.mendoza@empresa.com');

call sp_AgregarRepuesto('Filtro de aceite', 'Motor', 25.00, 45.00, 1);
call sp_AgregarRepuesto('Bujía NGK', 'Encendido', 15.00, 30.00, 2);
call sp_AgregarRepuesto('Pastillas de freno', 'Frenos', 120.00, 180.00, 3);
call sp_AgregarRepuesto('Amortiguador delantero', 'Suspensión', 350.00, 500.00, 4);
call sp_AgregarRepuesto('Batería 12V', 'Eléctrico', 600.00, 850.00, 5);
call sp_AgregarRepuesto('Correa de distribución', 'Motor', 200.00, 320.00, 6);
call sp_AgregarRepuesto('Radiador', 'Enfriamiento', 750.00, 1100.00, 7);
call sp_AgregarRepuesto('Alternador', 'Eléctrico', 900.00, 1300.00, 8);
call sp_AgregarRepuesto('Filtro de aire', 'Motor', 30.00, 60.00, 9);
call sp_AgregarRepuesto('Disco de freno', 'Frenos', 250.00, 400.00, 10);

call sp_AgregarVenta('2025-01-10', 2, 90.00, 1, 1);
call sp_AgregarVenta('2025-01-11', 4, 120.00, 2, 2);
call sp_AgregarVenta('2025-01-12', 1, 180.00, 3, 3);
call sp_AgregarVenta('2025-01-13', 2, 1000.00, 4, 4);
call sp_AgregarVenta('2025-01-14', 1, 850.00, 5, 5);
call sp_AgregarVenta('2025-01-15', 3, 960.00, 6, 6);
call sp_AgregarVenta('2025-01-16', 1, 1100.00, 7, 7);
call sp_AgregarVenta('2025-01-17', 2, 2600.00, 8, 8);
call sp_AgregarVenta('2025-01-18', 5, 300.00, 9, 9);
call sp_AgregarVenta('2025-01-19', 2, 800.00, 10, 10);
