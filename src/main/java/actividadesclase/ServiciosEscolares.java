package actividadesclase;

import actividadesclase.alumnos;
import actividadesclase.Queue;

public class ServiciosEscolares {


    private Queue<alumnos> queue;

    public ServiciosEscolares() { queue = new Queue<>(); }

    public void formarAlumno(alumnos a) {
        if(null ! = a){
            queue.enqueue(a);
        }
    }
}