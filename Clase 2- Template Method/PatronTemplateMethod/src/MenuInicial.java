import java.util.Scanner;

public class MenuInicial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean menuActivo = true;
        double sumaMenus = 0.000;
        while (menuActivo) {

            System.out.println("**********************************************");
            System.out.println("Menú de opciones:");
            System.out.println("1. Menu Clasico 1");
            System.out.println("2. Menu Infantil 2");
            System.out.println("3. Menu Vegetariano 3");
            System.out.println("4. Salir");
            System.out.println("**********************************************");
            System.out.print("Seleccione una opción del menu a preparar: \n ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1 - Menu Clasico ");
                    System.out.println("Este contiene Dos tajadas de pan de Hamburgesa, una loncha de queso " +
                            "Americano,una porcion de carne de hamburgesa y vegetales como: tomate, cebolla y pepinillos");
                    MenuClasico menuClasico = new MenuClasico("Menu Clasico",22000);
                    System.out.println(menuClasico.toString());
                    System.out.println("Gracias por su compra \n ");
                    sumaMenus+=menuClasico.getPrecioBase();
                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2 - Menu Infantil");
                    System.out.println("Este contiene Dos tajadas de pan de Hamburgesa, una loncha de queso \n " +
                            "Americano,una porcion de carne de hamburgesa y vegetales como: tomate,cebolla y pepinillos \n"+"+ Un juguete infantil ");

                        MenuInfantil menuInfantil = new MenuInfantil("Menu Infantil",22000,1);
                    System.out.println(menuInfantil.toString());
                    System.out.println("Gracias por su compra \n ");
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3");
                    System.out.println("Este contiene Dos tajadas de pan de Hamburgesa, una loncha de queso " +
                            "Americano,una porcion de carne de lentejas y vegetales como: tomate, cebolla y pepinillos");
                    MenuVegetariano menuVegetariano = new MenuVegetariano("Menu Vegetariano",22000,true,true,1);
                    System.out.println(menuVegetariano.toString());
                    System.out.println("Gracias por su compra \n ");
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                   menuActivo = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;

            }

        }
        scanner.close();
        System.out.println("La Suma total de los menus comprados es: ");
        System.out.println(sumaMenus);
    }
}
