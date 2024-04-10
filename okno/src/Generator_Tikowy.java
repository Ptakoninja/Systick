import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Generator_Tikowy extends Thread implements PulseSource {
	ActionListener k;
	private static final byte BURST_MODE = 0;
	private static final byte CONTINUOUS_MODE = 1;
	    
	
    private byte mode;
    private int pulseDelay;
    private int pulseCount;
    boolean enable;
    boolean alive;
    int licznik;
    SysTick systick = new SysTick();
    
	public Generator_Tikowy () {
		 mode = CONTINUOUS_MODE;
	     pulseDelay = 1000; // domyślnie 1000 ms
	     enable=false;
	     alive=true;
	     start();
	     licznik=0;
	}
	
	
	
	
	public void killThread() {
		alive=false;
	}       
	
	
	public void addActionListener(ActionListener pl) {
		// TODO Auto-generated method stub
		k=AWTEventMulticaster.add(k, pl);
		
	}

	@Override
	public void removeActionListener(ActionListener pl) {
		// TODO Auto-generated method stub
		k=AWTEventMulticaster.remove(k, pl);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		 enable=true;
			System.out.println("włączenie generatora");
		
	}
	public void run() {
		
		
		while(alive) {
			if(mode==CONTINUOUS_MODE) {
				generate();
			}
			else if(mode==BURST_MODE) {
				if(pulseCount>=licznik) {
				generate();
			}else {
				licznik=0;
				halt();
			}
				}
			
		}
	}
	public void generate() {
		if(enable) {
			
			licznik++;
			System.out.println(licznik);
			if(k!=null) k.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"ticki1"));
			try {
				Thread.sleep(pulseDelay);
				System.out.println(licznik+ " try lcizenie");
			} catch (InterruptedException e) {
				System.out.println(licznik+ " catch lcizenie");
			e.printStackTrace();
			
			}
		}
		else {
			try {
				Thread.sleep(pulseDelay);
			} catch (InterruptedException e) {
				
			e.printStackTrace();
				}
			}
	}
	@Override
	public void setMode(byte mode) {
		// TODO Auto-generated method stub
				if(mode == BURST_MODE || mode == CONTINUOUS_MODE )
				this.mode=mode;
				System.out.println("wybrano mode "+mode);
				
		
			
	}

	@Override
	public byte getMode() {
		// TODO Auto-generated method stub
		System.out.println("wybrany mode to "+mode);
		 return mode;
	}

	@Override
	public void halt() {
		// TODO Auto-generated method stub
		enable=false;
		System.out.println("wyłączenie generatora");
	}

	@Override
	public void setPulseDelay(int ms) {
		// TODO Auto-generated method stub
		System.out.println("ustawiam "+pulseDelay);
		this.pulseDelay = ms;
	}

	@Override
	public int getPulseDelay() {
		// TODO Auto-generated method stub
		System.out.println("wartość pulse delay "+pulseDelay);
		return pulseDelay;

	}

	@Override
	public void setPulseCount(int burst) {
		System.out.println("wartość pulse count"+pulseCount);
		if(burst>0) {
		pulseCount=burst-1;
		}
		
    }
	


}







