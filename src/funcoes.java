import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime ;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class funcoes {
    public static void displaypessoa(List<pessoa> peoples) {

        int index = 1;
        for (pessoa people : peoples) {
            System.out.println(" Numero de pessoas: " + index++ );
            System.out.println(" Name: " + people.getNome());
            System.out.println("Street Name: " + people.getEnderecos().get(0).getRua());
            System.out.println("Street Number: " + people.getEnderecos().get(0).getNumero());
        }

    }

    public static void searchForPerson(Scanner input, List<pessoa> peoples) {
        System.out.print("Digite o nome que deseja pesquisar ");
        String search = input.nextLine();

        pessoa foundPerson = null;

        for (pessoa person : peoples) {
            if (person.getNome().equalsIgnoreCase(search)) {
                foundPerson = person;
                break;
            }
        }

        if (foundPerson != null) {
            System.out.println("Detalhes");
            System.out.println("Nome: " + foundPerson.getNome());
            for (Enderecos address : foundPerson.getEnderecos()) {
                System.out.println("Rua: " + address.getRua());
                System.out.println("Numero: " + address.getNumero());
            }


            System.out.println("Editar os dados dessa pessoa?  (sim/nao)");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("sim")) {
                editPersonDetails(input, peoples);
                System.out.println("Dados atualizados com sucesso!");
            } else {
                System.out.println("Saindo sem realizar alteracoes.");
            }

        } else {
            System.out.println("Nome nao encontrado!");
        }
    }
    public static void editPersonDetails(Scanner input, List<pessoa> peoples) {
        System.out.println("Lista de pessoas:");
        int index = 1;
        for (pessoa person : peoples) {
            System.out.println(index + ". " + person.getNome() + " - ");
            index++;
        }
        System.out.println(index + ". sair");
        System.out.println("Digite o numero do cliente que deseja editar ou  " + index + " para sair:");

        int personNumber;
        try {
            personNumber = Integer.parseInt(input.nextLine());
            if (personNumber == index) {
                return;
            }

            pessoa selectedPerson = peoples.get(personNumber - 1);
            System.out.println("Editando dados de : " + selectedPerson.getNome());

            System.out.println(" Nome: " + selectedPerson.getNome());
            System.out.print("Digite um novo nome ou aperte ENTER para manter o mesmo: ");
            String newName = input.nextLine().trim();
            if (!newName.isEmpty()) {
                selectedPerson.setNome(newName);
            }

            System.out.println(" Idade: " + selectedPerson.getIdade());
            System.out.print("Digite uma nova idade ou aperte ENTER para manter o mesmo: ");
            Integer newage = input.nextInt();
            if (newage != null) {
                selectedPerson.setIdade(newage);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Numero invalido.");
        }
    }



    public static void makeOrder(int personNum, List<pessoa> peoples, List<Order> orders) {
        Scanner input = new Scanner(System.in);
        System.out.println("Detalhes do pedido " + (personNum + 1) );
        System.out.print("Pedido: ");
        String order = input.nextLine();

        System.out.print("Descricao: ");
        String description = input.nextLine();

        int quantity = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Quantidade: ");
            try {
                quantity = input.nextInt();
                valid = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Quantidade invalida, por favor difite um numero inteiro.");
                input.nextLine();
            }
        }
        input.nextLine();
        registerpessoa(peoples, personNum);

        orders.add(new Order(UUID.randomUUID(), order, description, quantity, LocalDateTime.now(), peoples.get(personNum)));

    }

    public static void registerpessoa(List<pessoa> peoples, int personNum) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite os dados do cliente " + (personNum + 1) );
        System.out.print("Nome: ");
        String name = input.nextLine();

        System.out.print("idade: ");
        int age = input.nextInt();




        Enderecos address = new Enderecos();
        input.nextLine();
        System.out.print("Nome da rua: ");
        address.setRua(input.nextLine());

        boolean validStreetNum = false;

        while (!validStreetNum) {
            System.out.print("Numero da rua: ");
            try {
                int streetNum = Integer.parseInt(input.nextLine());
                address.setNumero(streetNum);
                validStreetNum = true;
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido.");
            }
        }


        List<Enderecos> addresses = new ArrayList<>();
        addresses.add(address);

        peoples.add(new pessoa(name, age, addresses));


    }



    public static void showOpenOrders(Scanner input, List<Order> orders) {
        System.out.println("Peidos em aberto:");
        int index = 1;
        List<Integer> openOrderIndices = new ArrayList<>();
        for (Order order : orders) {
            if (!order.isFinishOrder() && !order.isMakingOrder()) {
                openOrderIndices.add(orders.indexOf(order));
                System.out.println(index + ". " + order.getOrderSnack() + " - " + order.getDescription());
                index++;
            }
        }

        System.out.println(index + ". sair");
        System.out.println("Selecione o numero de um pedido para comecar a faze-lo ou " + index + " para sair:");

        int orderNumber;
        try {
            orderNumber = Integer.parseInt(input.nextLine());
            if (orderNumber == index) {
                return;
            }

            int realOrderIndex = openOrderIndices.get(orderNumber - 1);
            orders.get(realOrderIndex).setMakingOrder(true);
            System.out.println("pedido #" + orderNumber + " esta sendo feito.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Numerode pedido invalido, selecione um valido.");
        }
    }
    public static void showClosedOrders(Scanner input, List<Order> orders) {
        System.out.println("Fechar pedidos:");
        int index = 1;
        List<Integer> closedOrderIndices = new ArrayList<>();
        for (Order order : orders) {
            if (order.isFinishOrder()) {
                closedOrderIndices.add(orders.indexOf(order));
                System.out.println(index + ". " + order.getOrderSnack() + " - " + order.getDescription());
                index++;
            }
        }

        System.out.println(index + ". Exit");
        System.out.println("Selecione um pedido para ver os detalhes e gerar o comprovante ou" + index + " para sair:");
        int orderNumber;
        try {
            orderNumber = Integer.parseInt(input.nextLine());
            if (orderNumber == index) {
                return;
            }

            int realOrderIndex = closedOrderIndices.get(orderNumber - 1);
            Order selectedOrder = orders.get(realOrderIndex);


        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("numero invalido, tente novamente.");
        }
    }


    public static void showProgressAndPrintOrder(Scanner input, List<Order> orders) {
        System.out.println("Pedidos sendo feitos:");
        int index = 1;
        List<Integer> inProgressOrderIndices = new ArrayList<>();
        for (Order order : orders) {
            if (order.isMakingOrder() && !order.isFinishOrder()) {
                inProgressOrderIndices.add(orders.indexOf(order));
                System.out.println(index + ". " + order.getOrderSnack() + " - " + order.getDescription());
                index++;
            }
        }

        if (inProgressOrderIndices.isEmpty()) {
            System.out.println("Nenhum pedido sendo feito no momento");
            return;
        }

        System.out.println(index + ". Exit");
        System.out.println("Digite o numero de um pedido para ver os detalhes ou" + index + " para sair:");

        int orderNumber;
        try {
            orderNumber = Integer.parseInt(input.nextLine());
            if (orderNumber == index) {
                return;
            }

            int realOrderIndex = inProgressOrderIndices.get(orderNumber - 1);
            Order selectedOrder = orders.get(realOrderIndex);
            pessoa orderPerson = selectedOrder.getpessoa();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm");
            String formattedDateTime = selectedOrder.getOrderTime().format(formatter);

            System.out.println("Detalhes do pedido");
            System.out.println(" ID: " + selectedOrder.getId());
            System.out.println(" Lanche: " + selectedOrder.getOrderSnack());
            System.out.println("Descricao: " + selectedOrder.getDescription());
            System.out.println("Quantidade: " + selectedOrder.getQuantity());
            System.out.println("Tempo do pedido: " + formattedDateTime);
            System.out.println("\n -------Detalhes do cliente---------");
            System.out.println("Nome: " + orderPerson.getNome());
            System.out.println("Idade: " + orderPerson.getIdade());
            for (Enderecos address : orderPerson.getEnderecos()) {
                System.out.println("Rua: " + address.getRua());
                System.out.println("Numero: " + address.getNumero());
            }


            System.out.println("Imprimir os detalhes do pedido? (sim/nao)");
            String choice = input.nextLine().trim().toLowerCase();
            if (choice.equals("sim")) {
                printOrderToFile(selectedOrder, orderPerson);
            }

            System.out.println("Encerrar o pedido? (sim/nao): ");
            String closeResponse = input.nextLine();

            if ("sim".equalsIgnoreCase(closeResponse)) {
                selectedOrder.setFinishOrder(true);
                System.out.println("pedido finalizado");
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Numero invalido");
        }

    }

    public static void printOrderToFile(Order order, pessoa orderPerson) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
            String fileName = "Pedido" + order.getId() + "_" + formatter.format(order.getOrderTime()) + ".txt";
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");

            writer.println("------------Detalhes-----------");
            writer.println("ID: " + order.getId());
            writer.println(" Lanche: " + order.getOrderSnack());
            writer.println("Descricao: " + order.getDescription());
            writer.println("Quantidade: " + order.getQuantity());
            writer.println("Horas: " + order.getOrderTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            writer.println("\n ----Detalhes do cliente ------");
            writer.println("Name: " + orderPerson.getNome());
            for (Enderecos address : orderPerson.getEnderecos()) {
                writer.println("Street Name: " + address.getRua());
                writer.println("Street Number: " + address.getNumero());
            }
            writer.println("-----------------------------------------");

            writer.close();
            System.out.println("pedido salvo em: " + fileName + ".txt");

        } catch (IOException e) {
            System.out.println("Erro ao gerar comprovante");
        }
    }
}