

//import static org.junit.Assert.assertEquals;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;

public class SysTick implements Cortex_M0_SysTick_Interface {

	private int rvr; // rejestr przeładowania
	private int cvr; // rejestr wartości bieżącje
	private int csr; // rejestr stanu
	ActionListener al;
	 public SysTick() {
	        this.rvr = 0;
	        this.cvr = 0;
	        this.csr = 0;
//	        this.countFlag = false;
//	        this.Clksrc = false; // 
//	        this.Tickint= false;
//	        this.Enable = false;
	    }
	public static void main(String[]args)  {
//        SysTick cortexM01 = new SysTick();
//        cortexM01.setSourceInternal(); 
//        cortexM01.setRVR(1);
//        cortexM01.setCVR(4);
//        cortexM01.setEnable();
//        cortexM01.tickInternal();
////        assertEquals(1, cortexM01.getCVR());
//        cortexM01.setRVR(0);
//        cortexM01.tickInternal();
////        assertEquals(0, cortexM01.getCVR());
////        assertEquals(0, cortexM01.getRVR());
//        cortexM01.tickInternal();
////        assertEquals(0, cortexM01.getCVR());
////        assertEquals(0, cortexM01.getRVR());
//        cortexM01.setRVR(2);
//        cortexM01.tickInternal();
////        assertEquals(0, cortexM01.getCVR()); ten nie dziala
////        assertEquals(2, cortexM01.getRVR());
//        cortexM01.setEnable();
//        cortexM01.tickInternal();
////        assertEquals(2, cortexM01.getCVR());
//        cortexM01.tickInternal();
////        assertEquals(1, cortexM01.getCVR());
//        cortexM01.tickInternal();
////        assertEquals(0, cortexM01.getCVR());
    }

public void tickInternal() {
            // TODO Auto-generated method stub
            // zegar wewnetrzny
            if ((csr & (1 << 2))!=0) {
            	ticki();
            }
            if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"tickIxternal"+ getCVR()));
        }

        @Override
        public void tickExternal() {
            //zergar zewnetrzny

            if ((csr & (1 << 2))==0) {
                ticki();
            }
        	if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"tickExternal"+ getCVR()));
    }
        
        public void ticki() {
        	if ((csr & (1 << 0))!=0) {
                cvr--;
                if (cvr == 0) {
                    if(rvr==0) {
                        csr &= ~(1 << 0);
                    }
                    csr |= (1 << 16); // Ustawia bit COUNTFLAG
                }
                else if(cvr < 0){
                    cvr=rvr;
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////
	@Override
	public void setRVR(int value) {
		// TODO Auto-generated method stub
		//System_RVR = 0 timer = 0
		//wyłącza timer, nawet jeśli timer jest włączony
//		if(value == 0) {
//			csr &= ~(1<<0);
//		}
		rvr = value & 0xFFFFFF;
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawienie wartości RVR"));
	}
	
	@Override
	public void setCVR(int value) {
		// TODO Auto-generated method stub
		/*
		 * wartoś system CVR jest niezna po resecie
		 */
		// Ograniczenie do 24 bitów
		cvr =0x000000;
		
		csr &= ~(1<<16);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawienie wartości CVR"));
	}

	@Override
	public void setCSR(int value) {
		// TODO Auto-generated method stub
		csr=value&0xFFFFFF;
//		csr &= ~(1 <<16);
//		Enable	 = (value & (1 << 0)) != 0;
//		Tickint	 = (value & (1 << 1)) != 0;
//		Clksrc 	 = (value & (1 << 2)) != 0; 
//		countFlag= (value & (1 << 16))!= 0; 
//		csr = csr &~(1<<16);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawienie wartości CSR"));
		
		
		
	}

	public void reset() {
		csr = 0x000000;
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"RESET"));
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setEnable() {
		// Ustaw bit ENABLE 
		//csr = csr | (1 << 0);
		csr |= (1 << 0); 
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Enable"));
		
	}

	public void setDisable() {
		 // Wyzeruj bit ENABLE 

        csr &= ~(1 << 0); 
        if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Disable"));
	}

	public void setSourceExternal() {
		 // Wyzeruj bit ENABLE 
//		csr |= (1 << 2);
		csr &= ~(1 << 2);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam Source External "));
	}

	public void setSourceInternal() {
		// Ustaw bit CLKSRC 
		csr |= (1 << 2);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam Source internal "));
//		csr &= ~(1 << 2); 
	}

	public void setInterruptEnable() {
		// Ustaw bit TICKINT 
		csr |= (1 << 1);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam Tickint true "));
	}

	public void setInterruptDisable() {
		// Wyzeruj bit TICKINT 
		csr &= ~(1 << 1);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam Tickint false "));
	}

	public void setCountFlagON() {
		
		
			csr|= (1<<16);
			if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam CountFlag true "));
		
		
		
	}
	
	public void setCountFlagOFF(){
		csr&= ~(1<<16);
		if(al!=null) al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Ustawiam CountFlag false "));
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getCVR() {
		// Odczyt z rejestru CV (0xE000E018)
		return cvr;
	}
	public int getRVR() {
		// Odczyt z rejestru RV (0xE000E014)
		return rvr;
	}

	public int getCSR() {
		// Aktualizuj stan rejestrĂłw przed odczytem
		int temp = csr;
		csr &= ~(1 <<16);
       return temp; 
	}
	public int isCSR() {
		return csr;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean getEnabled() {
		// TODO Auto-generated method stub
		// Sprawdź bit ENABLE (0xE000E010)
		int temp = (csr&(1<<0));
		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}	
	public boolean getInterrupt() {
		int temp = (csr&(1<<1));
		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
	public boolean getSource() {
		int temp = (csr&(1<<2));
		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
		
	}
	public boolean getCountFlag() {
		int temp = (csr&(1<<16));
		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean isCountFlag() {
		int temp = (csr&(1<<16));
//		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
	
	public boolean isEnableFlag() {
		int temp = (csr&(1<<0));
//		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
	@Override
	public boolean isInterruptFlag() {
		int temp = (csr&(1<<1));
//		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
	@Override
	public boolean isSourceFlag() {
		int temp = (csr&(1<<2));
//		csr &= ~(1<<16);
		if (temp<=0) return false;
		else
			return true;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addActinListener(ActionListener l) {
		al=AWTEventMulticaster.add(al, l);
	}
	public void removeActinListener(ActionListener l) {
		al=AWTEventMulticaster.remove(al, l);
	}
}