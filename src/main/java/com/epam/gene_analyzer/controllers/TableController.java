package com.epam.gene_analyzer.controllers;

import com.epam.gene_analyzer.model.RtModel;


/** Controller class for connecting model of table with interface of user
 *
 */
public class TableController {

    /** Field of table model
     *
     */
    private RtModel rtModel;

    /** Method for getting table model
     *
     * @param rtModel table model
     */
    public TableController(RtModel rtModel) {
        this.rtModel = rtModel;
    }

    /** Method for transfer index of deleted row of table
     *
     * @param idRow id of row
     */
    public void transferDeleteData(int idRow) {
    }
}
