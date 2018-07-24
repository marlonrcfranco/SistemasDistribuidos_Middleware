import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client2 {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
//		HelloService service = (HelloService) Naming.lookup("rmi://localhost:7777/teste");
//		System.out.println("-------" + service.echo("Hello Server"));
		ValidadorInterface oValidador = (ValidadorInterface)Naming.lookup("rmi://localhost:7777/teste");
		System.out.println("-------" + oValidador.sayHello());
	}
}