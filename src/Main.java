import java.util.List;
import java.util.Scanner;
import udla.adminus.mmunoz.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Estudiante[] estudiantes = new Estudiante[100];
    private static Docente[] docentes = new Docente[100];
    private static Administrativo[] administrativos = new Administrativo[100];
    private static Curso[] cursos = new Curso[100];
    private static Asignatura[] asignaturas = new Asignatura[100];

    private static int contadorEstudiantes = 0;
    private static int contadorDocentes = 0;
    private static int contadorAdministrativos = 0;
    private static int contadorCursos = 0;
    private static int contadorAsignaturas = 0;

    private static String cedulaActual = "";
    private static int tipoUsuarioActual = 0;
    private static boolean sesionIniciada = false;

    public static void main(String[] args) {
        int opcion = 0;

        while(opcion != 3) {
            mostrarMenuInicial();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    iniciarSesion();
                    if(sesionIniciada) {
                        menuPrincipal();
                        sesionIniciada = false;
                    }
                    break;
                case 2:
                    registrarse();
                    break;
                case 3:
                    System.out.println("\nGracias por usar ADMINUS! Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void mostrarMenuInicial() {
        System.out.println("\n========================================");
        System.out.println("      BIENVENIDO A ADMINUS");
        System.out.println("========================================");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void registrarse() {
        System.out.println("\n=== REGISTRO DE NUEVO USUARIO ===");
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1. Estudiante");
        System.out.println("2. Docente");
        System.out.println("3. Personal Administrativo");
        System.out.print("Opcion: ");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n--- DATOS PERSONALES ---");

        System.out.print("Cedula: ");
        String cedula = scanner.nextLine();
        if(cedula.isEmpty()) {
            System.out.println("ERROR: La cedula no puede estar vacia. Registro cancelado.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        if(nombre.isEmpty()) {
            System.out.println("ERROR: El nombre no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Genero: ");
        String genero = scanner.nextLine();
        if(genero.isEmpty()) {
            System.out.println("ERROR: El genero no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();
        if(direccion.isEmpty()) {
            System.out.println("ERROR: La direccion no puede estar vacia. Registro cancelado.");
            return;
        }

        System.out.print("Telefono: ");
        long telefono = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if(email.isEmpty()) {
            System.out.println("ERROR: El email no puede estar vacio. Registro cancelado.");
            return;
        }

        switch(tipo) {
            case 1:
                registrarNuevoEstudiante(cedula, nombre, edad, genero, direccion, telefono, email);
                break;
            case 2:
                registrarNuevoDocente(cedula, nombre, edad, genero, direccion, telefono, email);
                break;
            case 3:
                registrarNuevoAdministrativo(cedula, nombre, edad, genero, direccion, telefono, email);
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private static void registrarNuevoEstudiante(String cedula, String nombre, int edad,
                                                  String genero, String direccion, long telefono, String email) {
        System.out.println("\n--- DATOS ACADEMICOS DEL ESTUDIANTE ---");

        System.out.print("Nivel Educativo: ");
        String nivel = scanner.nextLine();
        if(nivel.isEmpty()) {
            System.out.println("ERROR: El nivel no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Paralelo: ");
        String paralelo = scanner.nextLine();
        if(paralelo.isEmpty()) {
            System.out.println("ERROR: El paralelo no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Nombre del Representante: ");
        String representante = scanner.nextLine();
        if(representante.isEmpty()) {
            System.out.println("ERROR: Los datos del representante no pueden estar vacios. Registro cancelado.");
            return;
        }

        Estudiante estudiante = new Estudiante(cedula, nombre, edad, genero, direccion,
                telefono, email, nivel, paralelo, representante);

        System.out.println("\n--- MATERIAS PREDEFINIDAS ---");
        Materia[] todasLasMaterias = Materia.values();
        for(int i = 0; i < todasLasMaterias.length; i++) {
            estudiante.addAsignatura(todasLasMaterias[i].name());
            System.out.println((i+1) + ". " + todasLasMaterias[i].name());
        }

        estudiantes[contadorEstudiantes] = estudiante;
        contadorEstudiantes++;

        System.out.println("\nEstudiante registrado exitosamente con todas las materias!");
        System.out.println(estudiante.mostrarInformacion());
    }

    private static void registrarNuevoDocente(String cedula, String nombre, int edad,
                                               String genero, String direccion, long telefono, String email) {
        System.out.println("\n--- DATOS LABORALES DEL DOCENTE ---");

        System.out.print("Sueldo Mensual: ");
        double sueldo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Jornada Laboral: ");
        String jornada = scanner.nextLine();
        if(jornada.isEmpty()) {
            System.out.println("ERROR: La jornada no puede estar vacia. Registro cancelado.");
            return;
        }

        System.out.print("Horas Trabajadas: ");
        int horas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        if(especialidad.isEmpty()) {
            System.out.println("ERROR: La especialidad no puede estar vacia. Registro cancelado.");
            return;
        }

        System.out.print("Titulo Academico: ");
        String titulo = scanner.nextLine();
        if(titulo.isEmpty()) {
            System.out.println("ERROR: El titulo no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Carga Horaria: ");
        int carga = scanner.nextInt();
        scanner.nextLine();

        Docente docente = new Docente(cedula, nombre, edad, genero, direccion, telefono,
                email, sueldo, jornada, horas, especialidad, titulo, carga);

        System.out.println("\n--- SELECCION DE MATERIA ---");
        System.out.println("Seleccione la materia que impartira:");
        Materia[] todasLasMaterias = Materia.values();
        for(int i = 0; i < todasLasMaterias.length; i++) {
            System.out.println((i+1) + ". " + todasLasMaterias[i].name());
        }
        System.out.print("Opcion: ");
        int opcionMateria = scanner.nextInt();
        scanner.nextLine();

        if(opcionMateria > 0 && opcionMateria <= todasLasMaterias.length) {
            docente.setMateriaAsignada(todasLasMaterias[opcionMateria - 1]);
            docente.addCurso(todasLasMaterias[opcionMateria - 1].name());
            System.out.println("Materia asignada: " + todasLasMaterias[opcionMateria - 1].name());
        } else {
            System.out.println("Opcion invalida. No se asigno materia.");
        }

        docentes[contadorDocentes] = docente;
        contadorDocentes++;

        System.out.println("\nDocente registrado exitosamente!");
        System.out.println(docente.mostrarInformacion());
    }

    private static void registrarNuevoAdministrativo(String cedula, String nombre, int edad,
                                                      String genero, String direccion, long telefono, String email) {
        System.out.println("\n--- DATOS LABORALES DEL ADMINISTRATIVO ---");

        System.out.print("Sueldo Mensual: ");
        double sueldo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Jornada Laboral: ");
        String jornada = scanner.nextLine();
        if(jornada.isEmpty()) {
            System.out.println("ERROR: La jornada no puede estar vacia. Registro cancelado.");
            return;
        }

        System.out.print("Horas Trabajadas: ");
        int horas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        if(cargo.isEmpty()) {
            System.out.println("ERROR: El cargo no puede estar vacio. Registro cancelado.");
            return;
        }

        System.out.print("Area: ");
        String area = scanner.nextLine();
        if(area.isEmpty()) {
            System.out.println("ERROR: El area no puede estar vacia. Registro cancelado.");
            return;
        }

        Administrativo admin = new Administrativo(cedula, nombre, edad, genero, direccion,
                telefono, email, sueldo, jornada, horas, cargo, area);

        administrativos[contadorAdministrativos] = admin;
        contadorAdministrativos++;

        System.out.println("\nAdministrativo registrado exitosamente!");
        System.out.println(admin.mostrarInformacion());
    }

    private static void iniciarSesion() {
        System.out.println("\n=== INICIAR SESION ===");
        System.out.print("Ingrese su cedula: ");
        String cedula = scanner.nextLine();

        if(cedula.isEmpty()) {
            System.out.println("ERROR: Debe ingresar una cedula valida.");
            System.out.println("Si no tiene cuenta, debe registrarse primero.");
            return;
        }

        for(int i = 0; i < contadorEstudiantes; i++) {
            if(estudiantes[i].getCedula().equals(cedula)) {
                cedulaActual = cedula;
                tipoUsuarioActual = 1;
                sesionIniciada = true;
                System.out.println("\nBienvenido/a " + estudiantes[i].getNombre());
                return;
            }
        }

        for(int i = 0; i < contadorDocentes; i++) {
            if(docentes[i].getCedula().equals(cedula)) {
                cedulaActual = cedula;
                tipoUsuarioActual = 2;
                sesionIniciada = true;
                System.out.println("\nBienvenido/a " + docentes[i].getNombre());
                return;
            }
        }

        for(int i = 0; i < contadorAdministrativos; i++) {
            if(administrativos[i].getCedula().equals(cedula)) {
                cedulaActual = cedula;
                tipoUsuarioActual = 3;
                sesionIniciada = true;
                System.out.println("\nBienvenido/a " + administrativos[i].getNombre());
                return;
            }
        }

        System.out.println("ERROR: Usuario no encontrado.");
        System.out.println("Debe registrarse primero.");
    }

    private static void menuPrincipal() {
        int opcion = 0;
        int opcionSalida = (tipoUsuarioActual == 1) ? 6 : (tipoUsuarioActual == 2) ? 7 : 8;

        while(opcion != opcionSalida) {
            mostrarMenuUsuario();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(tipoUsuarioActual) {
                case 1:
                    menuEstudiante(opcion);
                    break;
                case 2:
                    menuDocente(opcion);
                    break;
                case 3:
                    menuAdministrativo(opcion);
                    break;
            }
        }
    }

    private static void menuEstudiante(int opcion) {
        switch(opcion) {
            case 1:
                verMiInformacion();
                break;
            case 2:
                verMisNotas();
                break;
            case 3:
                verMiAsistencia();
                break;
            case 4:
                verMisDocentes();
                break;
            case 5:
                verMisMaterias();
                break;
            case 6:
                cerrarSesion();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private static void menuDocente(int opcion) {
        switch(opcion) {
            case 1:
                verMiInformacion();
                break;
            case 2:
                editarCalificaciones();
                break;
            case 3:
                verEstudiantes();
                break;
            case 4:
                editarAsistencia();
                break;
            case 5:
                verAsistencias();
                break;
            case 6:
                verCursosRegistrados();
                break;
            case 7:
                cerrarSesion();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }
    private static void menuAdministrativo(int opcion) {
        switch(opcion) {
            case 1:
                verMiInformacion();
                break;
            case 2:
                verCursosRegistrados();
                break;
            case 3:
                verAsignaturasRegistradas();
                break;
            case 4:
                verTodosEstudiantes();
                break;
            case 5:
                verTodosDocentes();
                break;
            case 6:
                cerrarSesion();
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private static void mostrarMenuUsuario() {
        System.out.println("\n========================================");
        System.out.println("      SISTEMA ADMINUS");
        System.out.println("========================================");

        switch(tipoUsuarioActual) {
            case 1:
                mostrarMenuEstudiante();
                break;
            case 2:
                mostrarMenuDocente();
                break;
            case 3:
                mostrarMenuAdministrativo();
                break;
        }

        System.out.print("Seleccione una opcion: ");
    }


    private static void mostrarMenuEstudiante() {
        System.out.println("1. Ver mi informacion");
        System.out.println("2. Ver mis Notas");
        System.out.println("3. Ver mi Registro de Asistencia");
        System.out.println("4. Ver mis Docentes");
        System.out.println("5. Ver mis Materias");
        System.out.println("6. Cerrar Sesion");
    }

    private static void mostrarMenuDocente() {
        System.out.println("1. Ver mi informacion");
        System.out.println("2. Editar Calificaciones");
        System.out.println("3. Ver Estudiantes");
        System.out.println("4. Editar Asistencia");
        System.out.println("5. Ver Asistencias");
        System.out.println("6. Ver Cursos");
        System.out.println("7. Cerrar Sesion");
    }

    private static void mostrarMenuAdministrativo() {
        System.out.println("1. Ver mi informacion");
        System.out.println("2. Ver Cursos Registrados");
        System.out.println("3. Ver Asignaturas Registradas");
        System.out.println("4. Ver Todos los Estudiantes");
        System.out.println("5. Ver Todos los Docentes");
        System.out.println("6. Cerrar Sesion");
    }

    private static void verMiInformacion() {
        switch(tipoUsuarioActual) {
            case 1:
                for(int i = 0; i < contadorEstudiantes; i++) {
                    if(estudiantes[i].getCedula().equals(cedulaActual)) {
                        System.out.println(estudiantes[i].mostrarInformacion());
                        break;
                    }
                }
                break;
            case 2:
                for(int i = 0; i < contadorDocentes; i++) {
                    if(docentes[i].getCedula().equals(cedulaActual)) {
                        System.out.println(docentes[i].mostrarInformacion());
                        break;
                    }
                }
                break;
            case 3:
                for(int i = 0; i < contadorAdministrativos; i++) {
                    if(administrativos[i].getCedula().equals(cedulaActual)) {
                        System.out.println(administrativos[i].mostrarInformacion());
                        break;
                    }
                }
                break;
        }
    }

    private static void verMisNotas() {
        for(int i = 0; i < contadorEstudiantes; i++) {
            if(estudiantes[i].getCedula().equals(cedulaActual)) {
                System.out.println("\n=== MIS NOTAS ===");
                System.out.println("Funcionalidad pendiente de implementar");
                break;
            }
        }
    }

    private static void verMiAsistencia() {
        for(int i = 0; i < contadorEstudiantes; i++) {
            if(estudiantes[i].getCedula().equals(cedulaActual)) {
                System.out.println("\n=== MI REGISTRO DE ASISTENCIA ===");
                System.out.println("Funcionalidad pendiente de implementar");
                break;
            }
        }
    }

    private static void verMisDocentes() {
        for(int i = 0; i < contadorEstudiantes; i++) {
            if(estudiantes[i].getCedula().equals(cedulaActual)) {
                System.out.println("\n=== MIS DOCENTES POR MATERIA ===");
                List<String> materias = estudiantes[i].getAsignaturas();

                for(String materia : materias) {
                    System.out.println("\nMateria: " + materia);
                    boolean docenteEncontrado = false;

                    for(int j = 0; j < contadorDocentes; j++) {
                        if(docentes[j].getMateriaAsignada() != null &&
                           docentes[j].getMateriaAsignada().name().equals(materia)) {
                            System.out.println("  Docente: " + docentes[j].getNombre());
                            System.out.println("  Especialidad: " + docentes[j].getEspecialidad());
                            docenteEncontrado = true;
                            break;
                        }
                    }

                    if(!docenteEncontrado) {
                        System.out.println("  No hay docente asignado");
                    }
                }
                break;
            }
        }
    }

    private static void verMisMaterias() {
        for(int i = 0; i < contadorEstudiantes; i++) {
            if(estudiantes[i].getCedula().equals(cedulaActual)) {
                System.out.println("\n=== MIS MATERIAS ===");
                List<String> listaMaterias = estudiantes[i].getAsignaturas();
                for(int j = 0; j < listaMaterias.size(); j++) {
                    System.out.println((j+1) + ". " + listaMaterias.get(j));
                }
                break;
            }
        }
    }

    private static void editarCalificaciones() {
        System.out.println("\n=== EDITAR CALIFICACIONES ===");
        System.out.println("Funcionalidad pendiente de implementar");
    }

    private static void verEstudiantes() {
        System.out.println("\n=== ESTUDIANTES ===");
        if(contadorEstudiantes == 0) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for(int i = 0; i < contadorEstudiantes; i++) {
                System.out.println("\n--- Estudiante " + (i+1) + " ---");
                System.out.println("Nombre: " + estudiantes[i].getNombre());
                System.out.println("Cedula: " + estudiantes[i].getCedula());
                System.out.println("Nivel: " + estudiantes[i].getNivelEducativo());
            }
        }
    }

    private static void editarAsistencia() {
        System.out.println("\n=== EDITAR ASISTENCIA ===");
        System.out.println("Funcionalidad pendiente de implementar");
    }

    private static void verAsistencias() {
        System.out.println("\n=== VER ASISTENCIAS ===");
        System.out.println("Funcionalidad pendiente de implementar");
    }

    private static void registrarCurso() {
        System.out.println("\n--- REGISTRO DE CURSO ---");

        System.out.print("Nivel: ");
        String nivel = scanner.nextLine();

        System.out.print("Paralelo: ");
        String paralelo = scanner.nextLine();

        System.out.print("Periodo Academico: ");
        String periodo = scanner.nextLine();

        Curso curso = new Curso(nivel, paralelo, periodo);

        System.out.print("Desea agregar asignaturas? (s/n): ");
        String respuesta = scanner.nextLine();

        if(respuesta.equalsIgnoreCase("s")) {
            System.out.print("Cuantas asignaturas? ");
            int num = scanner.nextInt();
            scanner.nextLine();

            for(int i = 0; i < num; i++) {
                System.out.print("Asignatura " + (i+1) + ": ");
                String asig = scanner.nextLine();
                curso.addAsignatura(asig);
            }
        }

        cursos[contadorCursos] = curso;
        contadorCursos++;

        System.out.println("\nCurso registrado exitosamente!");
        System.out.println(curso.mostrarInformacion());
    }

    private static void registrarAsignatura() {
        System.out.println("\n--- REGISTRO DE ASIGNATURA ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Horas Semanales: ");
        int horas = scanner.nextInt();
        scanner.nextLine();

        Asignatura asignatura = new Asignatura(nombre, codigo, horas);

        asignaturas[contadorAsignaturas] = asignatura;
        contadorAsignaturas++;

        System.out.println("\nAsignatura registrada exitosamente!");
        System.out.println(asignatura.mostrarInformacion());
    }

    private static void verCursosRegistrados() {
        System.out.println("\n=== CURSOS REGISTRADOS ===");
        if(contadorCursos == 0) {
            System.out.println("No hay cursos registrados.");
        } else {
            for(int i = 0; i < contadorCursos; i++) {
                System.out.println("\n--- Curso " + (i+1) + " ---");
                System.out.println(cursos[i].mostrarInformacion());
            }
        }
    }

    private static void verAsignaturasRegistradas() {
        System.out.println("\n=== ASIGNATURAS REGISTRADAS ===");
        if(contadorAsignaturas == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for(int i = 0; i < contadorAsignaturas; i++) {
                System.out.println("\n--- Asignatura " + (i+1) + " ---");
                System.out.println(asignaturas[i].mostrarInformacion());
            }
        }
    }

    private static void verTodosEstudiantes() {
        System.out.println("\n=== ESTUDIANTES REGISTRADOS ===");
        if(contadorEstudiantes == 0) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for(int i = 0; i < contadorEstudiantes; i++) {
                System.out.println("\n--- Estudiante " + (i+1) + " ---");
                System.out.println(estudiantes[i].mostrarInformacion());
            }
        }
    }

    private static void verTodosDocentes() {
        System.out.println("\n=== DOCENTES REGISTRADOS ===");
        if(contadorDocentes == 0) {
            System.out.println("No hay docentes registrados.");
        } else {
            for(int i = 0; i < contadorDocentes; i++) {
                System.out.println("\n--- Docente " + (i+1) + " ---");
                System.out.println(docentes[i].mostrarInformacion());
            }
        }
    }

    private static void cerrarSesion() {
        System.out.println("\nSesion cerrada exitosamente.");
        cedulaActual = "";
        tipoUsuarioActual = 0;
    }
}