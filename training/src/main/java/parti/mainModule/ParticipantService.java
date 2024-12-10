package parti.mainModule;

import java.util.ArrayList;

public class ParticipantService {

	public void displayList(ArrayList<Participant> participantList)
	{
		participantList.forEach((participant) -> print(participant));
	}
	
	public void print(Participant participant)
	{
		participant.displayValues();
	}
	
}
