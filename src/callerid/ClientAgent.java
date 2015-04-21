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
public class ClientAgent extends Agent
{
    private ClientGUI clientgui;
    private ACLMessage msg;
    private int step;
    public static String btn,text;
    
    @Override
    protected void takeDown() {
        super.takeDown(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setup() 
    {
        btn = null;
        text=null;
        step=0;
        clientgui = new ClientGUI();
        addBehaviour(new ClientStatus());
        clientgui.setVisible(true);
    }
    
    
    public class ClientStatus extends CyclicBehaviour
    {
        @Override
        public void action()
        {
            switch(step)
            {
                case 0 :
                    if((btn!=null) && (text!=null))
                    {
                        msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(new AID("server",AID.ISLOCALNAME));
                        msg.setLanguage("English");
                        msg.setOntology("Name-or-Number-ontology");
                        msg.setContent(btn+"="+text);
                        send(msg);
                        step=1;
                        msg=null;
                    }
                    break;
                case 1 :
                    msg = receive();
                    if(msg.getContent().substring(0,14).equalsIgnoreCase("search results"))
                    {
                        ClientGUI.showResult(msg.getContent());
                        msg = null;
                    }
                    
                    step = 2;
                    break;
                    
                case 2 :
                
                    
            }
            
           
        }
    }
    
    
    
}
