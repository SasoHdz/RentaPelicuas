/**
 * 
 */
package com.soa.enums;
/**
 * 
 */
public enum Crud {
    CREATE("create"),
    READ("read"), 
    UPDATE("update"),
    DELETE("delete");
    
    /** Valor asociado al enum.*/
    private String value;
    
    public String getValue() {
        return value;
    }
    /**
     * Constructor del enumerador.
     * @param value Valor asociado a cada elemento
     * */
    Crud(String value){
        this.value = value;
    }
    /**
     * Encontrar un elemento del enum por operacion
     * @param operacion Operacion a buscar
     * @return Elemento del enum correspondiente a la operacion.
     */
   public static Crud findByValue(String operacion) {
        // TODO Auto-generated method stub
        Crud[] values = Crud.values();
        for(Crud crud: values) {
           if (operacion.equalsIgnoreCase(crud.getValue())) {
               return crud;
           }
        }
        return null;
    }
}
