/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callerid;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author Nikhil
 */
public class ServerAgent extends Agent
{
    
    private ServerGUI servergui ;
    private ACLMessage msg;
    
    @Override
    protected void setup() 
    {
        servergui = new ServerGUI();
        addBehaviour(new ServerStatus());
        
        servergui.setVisible(true);
           
    }

    @Override
    protected void takeDown() 
    {
        super.takeDown();
       
    }

    public class ServerStatus extends CyclicBehaviour
    {
        @Override
        public void action()
        {
            msg = receive();
            if (msg != null)
            {
                String content = msg.getContent();
                if(content.substring(0, 3).equalsIgnoreCase("add"))
                {
                    //add the name and number to database!
                }
                else
                {
                    String result = servergui.searchTable(content);
                    msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID("client",AID.ISLOCALNAME));
                    msg.setLanguage("English");
                    msg.setOntology("Result-Name-and-Number");
                    msg.setContent(result);
                    send(msg);
                    msg=null;
                }
            }
            else
            {
                block();
            }
        }
        
    }
}
