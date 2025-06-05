public class AddressBook {
  private static final String lista_contactos = "contactos.csv";
    private static Map<String, String> contactos = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        load();
        int opcion;
        do {
            System.out.println("\nAgenda Telefónica");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    list();
                    break;
                case 2:
                    create();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    save();
                    System.out.println("Agenda guardada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(lista_contactos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 2);
                if (partes.length == 2) {
                    contactos.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No es posible cargar el archivo. Se creará uno nuevo al guardar.");
        }
    }

    public static void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(lista_contactos))) {
            for (Map.Entry<String, String> entrada : contactos.entrySet()) {
                bw.write(entrada.getKey() + "," + entrada.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo.");
        }
    }

    public static void list() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos.");
        } else {
            System.out.println("Contactos:");
            for (Map.Entry<String, String> entrada : contactos.entrySet()) {
                System.out.println(entrada.getKey() + " : " + entrada.getValue());
            }
        }
    }

    public static void create() {
        System.out.print("Introduce el número de teléfono: ");
        String numero = scanner.nextLine();
        if (contactos.containsKey(numero)) {
            System.out.println("Ya existe un contacto con ese número.");
            return;
        }
        System.out.print("Introduce el nombre del contacto: ");
        String nombre = scanner.nextLine();
        contactos.put(numero, nombre);
        System.out.println("Contacto agregado.");
    }

    public static void delete() {
        System.out.print("Introduce el número del contacto a eliminar: ");
        String numero = scanner.nextLine();
        if (contactos.remove(numero) != null) {
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("No se encontró ese número en la agenda.");
        }
    }
}
