/*
 * Copyright (C) 2020 Oscar Arenas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Oscar Arenas
 */
public class ArbolAVL {

    private NodoBinarioAVL raiz;
    private int n;

    public ArbolAVL() {
        raiz = null;
        n = 0;
    }

    public boolean agregar(int dato) {
        boolean inserto = insertar(dato);

        if (inserto) {
            balancear(dato);
        }

        return inserto;
    }

    // Inserta como en un ABB
    private boolean insertar(int dato) {
        NodoBinarioAVL actual = raiz;
        NodoBinarioAVL padre = null;

        while (actual != null) {
            padre = actual;
            if (dato < actual.dato) {
                actual = actual.hijoIzquierdo;
            } else if (dato > actual.dato) {
                actual = actual.hijoDerecho;
            } else {
                return false;
            }
        }

        NodoBinarioAVL nuevoNodo = new NodoBinarioAVL(dato);
        if (padre == null) {
            raiz = nuevoNodo;
        } else if (dato < padre.dato) {
            padre.hijoIzquierdo = nuevoNodo;
        } else {
            padre.hijoDerecho = nuevoNodo;
        }

        n++;
        return true;
    }

    private void balancear(int dato) {
        ArrayList<NodoBinarioAVL> ruta = camino(dato);

        for (int i = ruta.size() - 1; i >= 0; i--) {
            NodoBinarioAVL nodoA = ruta.get(i);
            nodoA.actualizarAltura();

            NodoBinarioAVL padreNodoA = (nodoA == raiz) ? null : ruta.get(i - 1);

            switch (nodoA.factorBalance()) {
                case -2:
                    if (nodoA.hijoIzquierdo.factorBalance() <= 0) {
                        rotacionII(nodoA, padreNodoA);
                    } else {
                        rotacionID(nodoA, padreNodoA);
                    }
                    break;
                case 2:
                    if (nodoA.hijoDerecho.factorBalance() >= 0) {
                        rotacionDD(nodoA, padreNodoA);
                    } else {
                        rotacionDI(nodoA, padreNodoA);
                    }
                    break;
            }
        }
    }

    // Rotación Izquierda-Izquierda
    private void rotacionII(NodoBinarioAVL nodoA, NodoBinarioAVL padreNodoA) {
        NodoBinarioAVL nodoB = nodoA.hijoIzquierdo;

        if (nodoA == raiz) {
            raiz = nodoB;
        } else if (padreNodoA.hijoIzquierdo == nodoA) {
            padreNodoA.hijoIzquierdo = nodoB;
        } else {
            padreNodoA.hijoDerecho = nodoB;
        }

        nodoA.hijoIzquierdo = nodoB.hijoDerecho;
        nodoB.hijoDerecho = nodoA;

        nodoA.actualizarAltura();
        nodoB.actualizarAltura();
    }

    // Rotación Izquierda-Derecha
    private void rotacionID(NodoBinarioAVL nodoA, NodoBinarioAVL padreNodoA) {
        NodoBinarioAVL nodoB = nodoA.hijoIzquierdo;
        NodoBinarioAVL nodoC = nodoB.hijoDerecho;

        if (nodoA == raiz) {
            raiz = nodoC;
        } else if (padreNodoA.hijoIzquierdo == nodoA) {
            padreNodoA.hijoIzquierdo = nodoC;
        } else {
            padreNodoA.hijoDerecho = nodoC;
        }

        nodoA.hijoIzquierdo = nodoC.hijoDerecho;
        nodoB.hijoDerecho = nodoC.hijoIzquierdo;
        nodoC.hijoIzquierdo = nodoB;
        nodoC.hijoDerecho = nodoA;

        nodoA.actualizarAltura();
        nodoB.actualizarAltura();
        nodoC.actualizarAltura();
    }

    // Rotación Derecha-Derecha
    private void rotacionDD(NodoBinarioAVL nodoA, NodoBinarioAVL padreNodoA) {
        NodoBinarioAVL nodoB = nodoA.hijoDerecho;

        if (nodoA == raiz) {
            raiz = nodoB;
        } else if (padreNodoA.hijoIzquierdo == nodoA) {
            padreNodoA.hijoIzquierdo = nodoB;
        } else {
            padreNodoA.hijoDerecho = nodoB;
        }

        nodoA.hijoDerecho = nodoB.hijoIzquierdo;
        nodoB.hijoIzquierdo = nodoA;

        nodoA.actualizarAltura();
        nodoB.actualizarAltura();
    }

    // Rotación Derecha-Izquierda
    private void rotacionDI(NodoBinarioAVL nodoA, NodoBinarioAVL padreNodoA) {
        NodoBinarioAVL nodoB = nodoA.hijoDerecho;
        NodoBinarioAVL nodoC = nodoB.hijoIzquierdo;

        if (nodoA == raiz) {
            raiz = nodoC;
        } else if (padreNodoA.hijoIzquierdo == nodoA) {
            padreNodoA.hijoIzquierdo = nodoC;
        } else {
            padreNodoA.hijoDerecho = nodoC;
        }

        nodoA.hijoDerecho = nodoC.hijoIzquierdo;
        nodoB.hijoIzquierdo = nodoC.hijoDerecho;
        nodoC.hijoIzquierdo = nodoA;
        nodoC.hijoDerecho = nodoB;

        nodoA.actualizarAltura();
        nodoB.actualizarAltura();
        nodoC.actualizarAltura();
    }

    private ArrayList<NodoBinarioAVL> camino(int dato) {
        ArrayList<NodoBinarioAVL> ruta = new ArrayList<>();

        NodoBinarioAVL actual = raiz;

        while (actual != null) {
            ruta.add(actual);

            if (dato < actual.dato) {
                actual = actual.hijoIzquierdo;
            } else if (dato > actual.dato) {
                actual = actual.hijoDerecho;
            } else {
                break;
            }
        }
        return ruta;
    }

    public boolean eliminar(int dato) {
        NodoBinarioAVL actual = raiz;
        NodoBinarioAVL padre = null;

        while (actual != null) {
            if (dato < actual.dato) {
                padre = actual;
                actual = actual.hijoIzquierdo;
            } else if (dato > actual.dato) {
                padre = actual;
                actual = actual.hijoDerecho;
            } else {
                break;
            }
        }
        if (actual == null) {
            return false;
        }

        //Caso 1: el actual no tiene hijoIzquierdo
        if (actual.hijoIzquierdo == null) {
            if (padre == null) {
                raiz = actual.hijoDerecho;
            } else {
                if (dato < padre.dato) {
                    padre.hijoIzquierdo = actual.hijoDerecho;
                } else {
                    padre.hijoDerecho = actual.hijoDerecho;
                }
                balancear(padre.dato);
            }
        } else {
            // Caso 2: El actual tiene hijo izquierdo
            NodoBinarioAVL padreMasALaDerecha = actual;
            NodoBinarioAVL masALaDerecha = actual.hijoIzquierdo;

            while (masALaDerecha.hijoDerecho != null) {
                padreMasALaDerecha = masALaDerecha;
                masALaDerecha = masALaDerecha.hijoDerecho;
            }
            actual.dato = masALaDerecha.dato;

            if (padreMasALaDerecha.hijoDerecho == masALaDerecha) {
                padreMasALaDerecha.hijoDerecho = masALaDerecha.hijoIzquierdo;
            } else {
                padreMasALaDerecha.hijoIzquierdo = masALaDerecha.hijoIzquierdo;
            }
            balancear(padreMasALaDerecha.dato);
        }
        n--;
        return true;
    }

    public int tamanio() {
        return n;
    }

    public boolean esVacio() {
        return n == 0;
    }

    public void imprimir() {
        imprimir(raiz, "");
    }

    private void imprimir(NodoBinarioAVL r, String espacios) {
        if (r != null) {
            imprimir(r.hijoDerecho, espacios + "   ");
            System.out.println(espacios + r.dato);
            imprimir(r.hijoIzquierdo, espacios + "   ");
        }
    }
}
