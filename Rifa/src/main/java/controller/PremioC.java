package controller;

import static com.sun.javafx.geom.Curve.next;
import dao.PersonaImpl;
import dao.PremioImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Persona;
import model.Premio;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named(value = "premioC")  //ManagedBean
@SessionScoped
public class PremioC implements Serializable {

    private Premio premio;
    private PremioImpl premiodao;
    private List<Premio> listapremio;

    private Persona persona;
    private PersonaImpl dao;
    private List<Persona> listapersona;
    private Integer intentos = 0;
    private String ganador;
    private boolean bloquear;

    public PremioC() {
        persona = new Persona();
        dao = new PersonaImpl();
        listapersona = new ArrayList<>();
        premiodao = new PremioImpl();
        premio = new Premio();
        listapremio = new ArrayList<>();
    }

    public void registrarPremio() {
        try {
            premiodao.registrar(premio);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarEstadoPremio(Premio pre) {
        try {
            premiodao.cambiarEstado(pre);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarNumero() {
        try {
            persona.setNumper(numRandom(0, 4));
//            int number;
//            String finalNumber = "";
//
//            number = (int) (10000 * Math.random());
//
//            finalNumber = "" + number;
//
//            for (int i = finalNumber.length(); i < 4; i++) {
//                finalNumber = "0" + finalNumber;
//            }
//
//            System.out.print(finalNumber);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() {
        try {
            dao.registrar(persona);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
            listar();
//            limpiar();
            persona.setNomper("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarEstadoPersona(Persona per) {
        try {
            dao.cambiarEstado(per);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            listapersona = dao.listar();
            listapremio = premiodao.listar();
            for (Persona persona1 : listapersona) {
                System.out.println(persona1);
            }
            generarNumero();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiar() {
        try {
            persona = new Persona();
            premio = new Premio();
        } catch (Exception e) {
        }
    }

    public void sortear() {
        try {
            if (intentos != null) {
                bloquear = true;
            }
            Random aleatorio = new Random();
            Persona sorteo = dao.listar().get(aleatorio.nextInt(dao.listar().size()));
            System.out.println(sorteo.getNomper() + " " + sorteo.getIdper());
            setGanador(sorteo.getNomper());
            intentos--;
            System.out.println(intentos);
            if (intentos == 2) {
                esperar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Primer intento", "Rifa Nº" + sorteo.getNumper()));
            }
            if (intentos == 1) {
                esperar();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Segundo intento", "Rifa Nº" + sorteo.getNumper()));
            }
            if (intentos == 0) {
                bloquear = false;
                Premio pre = premiodao.mostrarPremio(premio.getIdpre());
                setGanador(sorteo.getNomper() + " " + pre.getNompre() + " " + pre.getDespre());
                esperar();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Tecer Intento", "Ganador Nº" + sorteo.getNumper()));
                premiodao.quitarPrimerGanador(premio.getIdpre());
                dao.quitarPersonaGanador(sorteo.getIdper());
            }
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void esperar() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrar() throws Exception {
        Premio pre = premiodao.mostrarPremio(11);
        System.out.println(pre.getNompre() + pre.getDespre());
    }

    public static void main(String[] args) throws Exception {
        PremioC p = new PremioC();
        p.mostrar();

    }

    public static int numRandom(int result, int length) {
        //Result Donde guardo el Resultado Length variable que indica el tamaño de mi numero.    
        //Array donde guardo los randoms    
        int[] arrayRandom = new int[length];
        //Variable donde genero numeros por separado    
        int pos;
        //Variable donde eligo el Rango de Numeros     
        int nNums = 9;//En este caso del 1 al 9.
        //Creo un objeto Pila 
        Stack< Integer> pila = new Stack< Integer>();
        //For para generar los numeros
        for (int i = 0; i < arrayRandom.length; i++) {
            //Genero un numero random    
            pos = (int) Math.floor(Math.random() * nNums + 1);
            //Lo guardo en el array
            arrayRandom[i] = pos;

            //Si la pila ya contiene el numero 
            while (pila.contains(pos)) {
                //Vuelvo a generar un numero random  hasta que no se repita   
                pos = (int) Math.floor(Math.random() * nNums);
                arrayRandom[i] = pos;
            }
            //Guardo en el Stack (pila)
            pila.push(pos);
        }
        //y los muestro
        //System.out.println("Núm. aleatorios sin repetición:");
        System.out.println(pila.toString());

        //Lo convierto a un solo numero entero
        //Variable para Conseguir decena/centena/ Etc...
        int multiplicador = 1;
        //Segun el tamaño del numero (length)  
        for (int i = 1; i < length; i++) {

            multiplicador = multiplicador * 10;
        }
        //Convierto el Array de numeros aleatorios en un solo entero      
        for (int i = 0; i < length; i++) {
            //Multiplicando por el mas alto     
            result += (arrayRandom[i] * multiplicador);
            //Decremento el multiplicador        
            multiplicador = multiplicador / 10;

        }

        return result;

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public List<Persona> getListapersona() {
        return listapersona;
    }

    public void setListapersona(List<Persona> listapersona) {
        this.listapersona = listapersona;
    }

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public boolean isBloquear() {
        return bloquear;
    }

    public void setBloquear(boolean bloquear) {
        this.bloquear = bloquear;
    }

    public PremioImpl getPremiodao() {
        return premiodao;
    }

    public void setPremiodao(PremioImpl premiodao) {
        this.premiodao = premiodao;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    public List<Premio> getListapremio() {
        return listapremio;
    }

    public void setListapremio(List<Premio> listapremio) {
        this.listapremio = listapremio;
    }

}
