/*
name: Joshua Horton
netID: jhorto5
*/

public class getMove extends Move{
   private Character c;
   private Place p;
   private String s; //artifact name
   
   public getMove(Character c, Place p, String s){
     this.p = p;
     this.c = c;
     this.s = s;
     return;
   }
   
   @Override
   public void execute(){
     Artifact s = p.removeArtifactByName(s);
     if(a!=null){
			  c.addArtifact(s); 
			  System.out.println("\n(" + c.name() + " grabbed " + s.name() + ")");
		 }
     return;
   }
}
