import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<pessoa> pessoa = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        int personNum = 0;

        while (true) {
            displayMenu();
            int option;
            try {
                option = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("opcao inv1alida, selecione um numero");
                continue;
            }

            switch (option) {
                case 0:
                    funcoes.registerpessoa(pessoa, personNum);
                    personNum++;
                    break;
                case 1:
                    funcoes.displaypessoa(pessoa);
                    break;
                case 2:
                    funcoes.searchForPerson(input, pessoa);
                    break;
                case 3:
                    funcoes.makeOrder(personNum, pessoa, orders);
                    break;
                case 4:
                    funcoes.showOpenOrders(input, orders);
                    break;
                case 5:
                    funcoes.showClosedOrders(input, orders);
                    break;
                case 6:
                    funcoes.showProgressAndPrintOrder(input, orders);
                    break;
                case 7:
                    System.out.println("saindo!");
                    return;
                case 8:
//                    changeLanguage();
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
            }
        }
    }

    private static void displayMenu() {

        System.out.println("---------------------------------------");
        System.out.println("           PIZZARIA MESTRE DOS GADOS          ");
        System.out.println("---------------------------------------");
        System.out.println("0 | Novo cliente");
        System.out.println("1 | Listar pessoas");
        System.out.println("2 | Buscar pessoas e editar");
        System.out.println("3 | Realizar ordem");
        System.out.println("4 | Ordens abertas");
        System.out.println("5 | Ordens encerradas");
        System.out.println("6 | Progresso de pedidos");
        System.out.println("7 | Sair");
        System.out.println("--------------------------------------------");

    }

}