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
package pruebaarbolavl;

import edu.oharenas.aleatorio.Aleatorio;
import modelo.ArbolAVL;

/**
 *
 * @author Oscar Arenas
 */
public class PruebaArbolAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolAVL arbolBalanceado = new ArbolAVL();

        arbolBalanceado.agregar(25);
        arbolBalanceado.agregar(20);
        arbolBalanceado.agregar(5);
        arbolBalanceado.agregar(34);
        arbolBalanceado.agregar(50);
        arbolBalanceado.agregar(30);
        arbolBalanceado.agregar(10);

        arbolBalanceado.imprimir();

        ArbolAVL arbolBalanceado2 = new ArbolAVL();

        int n = 25;
        for (int i = 0; i < n; i++) {
            arbolBalanceado2.agregar(Aleatorio.entero(1, 4 * n));
        }

        System.out.println("Tamaño: " + arbolBalanceado2.tamanio());

        arbolBalanceado2.imprimir();
    }
}
