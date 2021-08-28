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

/**
 *
 * @author Oscar Arenas
 */
public class NodoBinarioAVL {

    int dato;
    NodoBinarioAVL hijoIzquierdo;
    NodoBinarioAVL hijoDerecho;
    int altura;

    public NodoBinarioAVL(int dato) {
        this.dato = dato;
        hijoIzquierdo = null;
        hijoDerecho = null;
        altura = 1;
    }

    public void actualizarAltura() {
        if (esHoja()) {
            altura = 1;
        } else if (hijoIzquierdo == null) {
            altura = hijoDerecho.altura + 1;
        } else if (hijoDerecho == null) {
            altura = hijoIzquierdo.altura + 1;
        } else {
            altura = Math.max(hijoIzquierdo.altura, hijoDerecho.altura) + 1;
        }
    }

    public int factorBalance() {
        if (esHoja()) {
            return 0;
        } else if (hijoIzquierdo == null) {
            return hijoDerecho.altura;
        } else if (hijoDerecho == null) {
            return -hijoIzquierdo.altura;
        } else {
            return hijoDerecho.altura - hijoIzquierdo.altura;
        }
    }

    public boolean esHoja() {
        return hijoIzquierdo == null && hijoDerecho == null;
    }

    public boolean tieneHijoIzquierdo() {
        return hijoIzquierdo != null;
    }

    public boolean tieneHijoDerecho() {
        return hijoDerecho != null;
    }
}
