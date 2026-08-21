# Plan de Implementación: Navegación Inferior y Pantallas XML

Este plan detalla los pasos para agregar una barra de navegación inferior y tres pantallas (Home, Registro y Lista de Usuarios) utilizando Fragmentos y el componente de Navegación de Jetpack.

## Cambios Propuestos

### Configuración de Dependencias

#### [MODIFY] [libs.versions.toml](file:///C:/Developt/Android/AppClase/gradle/libs.versions.toml)
Agregar las versiones y librerías de `androidx.navigation`.

#### [MODIFY] [build.gradle.kts](file:///C:/Developt/Android/AppClase/app/build.gradle.kts)
Aplicar las dependencias de navegación.

### Interfaz de Usuario (Layouts)

#### [NEW] [fragment_home.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/layout/fragment_home.xml)
Pantalla de bienvenida simple.

#### [NEW] [fragment_register.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/layout/fragment_register.xml)
Formulario con campos de Nombre y Correo.

#### [NEW] [fragment_user_list.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/layout/fragment_user_list.xml)
Contenedor para el listado de usuarios (RecyclerView).

#### [NEW] [item_user.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/layout/item_user.xml)
Diseño de cada fila en la lista de usuarios.

#### [NEW] [menu_bottom_nav.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/menu/menu_bottom_nav.xml)
Definición de los ítems de la barra inferior.

#### [NEW] [nav_graph.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/navigation/nav_graph.xml)
Grafo de navegación que conecta los fragmentos.

#### [MODIFY] [activity_main.xml](file:///C:/Developt/Android/AppClase/app/src/main/res/layout/activity_main.xml)
Reemplazar el contenido actual con `FragmentContainerView` y `BottomNavigationView`.

### Lógica de Aplicación (Kotlin)

#### [NEW] [User.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/User.kt)
Modelo de datos para el usuario.

#### [NEW] [HomeFragment.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/HomeFragment.kt)
#### [NEW] [RegisterFragment.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/RegisterFragment.kt)
#### [NEW] [UserListFragment.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/UserListFragment.kt)
Implementación de los fragmentos, incluyendo la carga de datos aleatorios en el listado.

#### [NEW] [UserAdapter.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/UserAdapter.kt)
Adaptador para el RecyclerView de usuarios.

#### [MODIFY] [MainActivity.kt](file:///C:/Developt/Android/AppClase/app/src/main/java/com/example/appclase/MainActivity.kt)
Configurar el `NavController` para manejar la navegación.

## Plan de Verificación

### Pruebas Manuales
1. Ejecutar la aplicación.
2. Verificar que se muestra la pantalla de bienvenida.
3. Navegar a la pantalla de Registro y verificar los campos.
4. Navegar a la lista de Usuarios y verificar que aparecen datos aleatorios.
5. Probar que al cambiar de pestaña en la barra inferior, el contenido se actualiza correctamente.
