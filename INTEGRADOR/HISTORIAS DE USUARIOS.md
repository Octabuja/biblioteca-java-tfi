📄 HISTORIAS DE USUARIO – Sistema de Gestión de Biblioteca

Versión del documento: 1.0
Fecha: 2025
Proyecto: Sistema de Biblioteca (TFI – Java + MySQL)



📘 HU-01 – Registrar Ficha Bibliográfica

Historia

Como bibliotecario
quiero registrar una nueva ficha bibliográfica
para almacenar información catalogada de cada libro y mantener la organización del material.

Criterios de aceptación

Se debe ingresar ISBN obligatorio y único.

No debe permitir campos obligatorios vacíos.

La ficha debe guardarse con estado activo (eliminado = false).

Debe asignarse un ID generado automáticamente.

📘 HU-02 – Consultar Ficha Bibliográfica

Historia

Como bibliotecario
quiero buscar una ficha bibliográfica por su ID
para ver sus datos y verificar información antes de asociarla o modificarla.

Criterios de aceptación

Debe mostrar los datos completos si existe.

Debe indicar si el ID ingresado no corresponde a una ficha existente o está eliminada.

📘 HU-03 – Actualizar Ficha Bibliográfica

Historia

Como bibliotecario
quiero actualizar la información de una ficha bibliográfica
para corregir datos o mejorarlos sin crear nuevos registros.

Criterios de aceptación

No debe permitir actualizar si el ID no existe.

Debe validar nuevamente los campos obligatorios.

Debe guardarse sin crear una nueva ficha.

📘 HU-04 – Eliminar Ficha Bibliográfica (baja lógica)

Historia

Como administrador
quiero eliminar lógicamente una ficha
para mantener histórico sin perder datos guardados.

Criterios de aceptación

Debe cambiar eliminado = true.

No debe eliminar físicamente el registro.

No debe permitir asociarla a nuevos libros después de eliminarla.

📗 HU-05 – Registrar Libro

Historia

Como bibliotecario
quiero registrar un libro indicando su ficha asociada
para tener un catálogo completo y correctamente indexado.

Criterios de aceptación

Título y autor son obligatorios.

Debe existir previamente la ficha bibliográfica ingresada.

Debe guardarse con estado activo.

El libro no puede existir sin ficha asignada.

📗 HU-06 – Consultar Libro

Historia

Como bibliotecario o usuario
quiero consultar los datos de un libro mediante su ID
para visualizar su información junto con la ficha asociada.

Criterios de aceptación

Debe mostrar datos del libro y su ficha.

Debe indicar si el ID ingresado no existe.

📗 HU-07 – Listar Libros

Historia

Como encargado de biblioteca
quiero listar todos los libros activos
para tener una vista general del catálogo disponible.

Criterios de aceptación

No deben mostrarse libros marcados como eliminados.

Debe visualizarse al menos ID, título y autor.

📗 HU-08 – Actualizar Libro

Historia

Como bibliotecario
quiero actualizar los datos de un libro existente
para corregir o completar información sin generar registros duplicados.

Criterios de aceptación

Debe validar nuevamente los datos requeridos.

No se permite actualizar si el ID no existe o está eliminado.

📗 HU-09 – Eliminar Libro (baja lógica)

Historia

Como administrador
quiero poder eliminar un libro sin borrar los datos físicamente
para mantener histórico, referencias y evitar pérdida de información.

Criterios de aceptación

eliminado debe pasar a true.

No debe mostrarlo en listados posteriores.

No debe afectar su ficha asociada.

📌 Observaciones finales

Las funcionalidades fueron desarrolladas siguiendo arquitectura en capas (Entities, DAO, Service y Main).

Todo registro eliminado debe permanecer en BD para auditoría.

Los mensajes al usuario deben ser claros y evitar términos técnicos.