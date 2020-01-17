import java.io.*;
import java.io.FileReader;
import java.util.Iterator;
import javax.swing.JFileChooser;  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.Desktop;
import java.net.URI;

public class Jsonview{
	
public static void main(String[] args) throws Exception{
	JSONParser parser = new JSONParser();
	FileChooser fc= new FileChooser();
	fc.startDisplay(fc);
	String sourcefile=(fc.f.getName()).split("\\.json")[0]+".html";
	String name="";
	try {
        Object obj = parser.parse(new FileReader(fc.f));
        JSONObject jsonObject = (JSONObject) obj;
		name = (String) jsonObject.toString();          
    }catch(Exception e) {
			e.printStackTrace();
	}
	File f = new File(sourcefile);
	BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write("<!doctype html>\n"+"<html>\n"+"<head>\n"+"<style>\n"+
			"\tbody{\n"+"\t\ttext-align: left;\n"+"\t\tbackground-color:#fffbd1;\n"+"\t\tfont-family: arial;\n"+"\t}\n"+
			"\tdiv{\n"+"\t\tborder:1px groove #c5c6c4;\n"+"\t\tpadding : 10px;\n"+"\t}\n"+
			"\tp{\n"+"\t\tborder:2px inset #d3d2d1;\n"+"\t\tpadding : 10px;\n"+"\t}\n"+
			"\t.expand{\n"+"\t\tbackground-color:#f7d796;\n"+"\t\tcursor: pointer;\n"+"\t}\n"+
			"\t.dis{\n"+"\t\tcursor: pointer;\n"+"\t\tcolor:#470000;\n"+"\t}\n"+
			"\t.hide{\n\t\tborder:1px solid red;\n\t}\n"+
			"\t.a{\n\t\tcolor:grey;\n\t}\n"+
			"</style>\n"+"</head>\n"+
			"<body>\n"+"\t"+"<p class='a cloud' onclick='hide()' style='font-size: 11.791pt; "+
			"position: absolute; top: 32.4554px; left: 17.9368px; z-index: 15; opacity: 0.469104;'>"+
			"click on empty box to expand</p>\n"+"\t<ul id='maindiv' ></ul>\n"+
			"\t<script src='jquery-3.1.1.min.js'></script>\n"+"<script>\n\n"+
			"var adz=[],ado=[],adt=[],adl=[];\n"+
			"for(i=0;i<=document.getElementsByClassName('cloud').length;i++){\n"+
			"\tadz[i]=1;\n\tado[i]=1;\n\tadt[i]=1;\n\tadl[i]=1;\n}\n"+
			"function hide(){\n\tvar ch=document.getElementsByClassName('cloud');\n"+
			"\tfor(i=0;i<ch.length;i++){\n\t\td=ch[i];\n"+
			"\t\td.style.display='none';\n\t}\n}\n\n"+
			"setInterval(anim,20);\n"+"function anim(){\n"+
			"\tvar ch=document.getElementsByClassName('cloud');\n"+
			"\tvar j=0,vadz=0,vado=0,vadt=0,vadl=0;\n"+
			"\tfor(i=0;i<ch.length;i++){\n"+"\t\td=ch[i];\n"+
			"\t\t/*vadz=Math.floor(Math.random() * (5-1+1))+1;\n"+
			"\t\tvado=(Math.random());\n"+"\t\tvadt=Math.floor(Math.random() * (5-1+1))+1;\n"+
			"\t\tvadl=Math.floor(Math.random() * (5-1+1))+1;*/\n"+"\t\tvadz=1;\n"+
			"\t\tvado=0.0168;\n"+"\t\tvadt=1;\n"+"\t\tvadl=1;\n"+"\t\tj++;\n"+
			"\t\tif(parseFloat(d.style.zIndex)>=49){\n"+"\t\t\tadz[j]=-1;\n"+"\t\t}\n"+
			"\t\telse if(parseFloat(d.style.zIndex)<=0){\n"+"\t\t\tadz[j]=1;\n"+"\t\t}\n"+
			"\t\tif(parseFloat(d.style.opacity)>=1.0){\n"+"\t\t\tado[j]=-1;\n"+"\t\t}\n"+
			"\t\telse if(parseFloat(d.style.opacity)<=0.02){\n"+"\t\t\tado[j]=1;\n"+"\t\t}\n"+
			"\t\tif(parseFloat(d.style.top)>=window.screen.availHeight-130){\n"+"\t\t\tadt[j]=-1;\n"+"\t\t}\n"+
			"\t\telse if(parseFloat(d.style.top)<=-10){\n"+"\t\t\tadt[j]=1;\n"+"\t\t}\n"+
			"\t\tif(parseFloat(d.style.left)>=window.screen.availWidth-130){\n"+"\t\t\tadl[j]=-1;\n"+"\t\t}\n"+
			"\t\telse if(parseFloat(d.style.left)<=-10){\n"+"\t\t\tadl[j]=1;\n"+"\t\t}\n"+
			"\t\t//console.log(d.style.zIndex+' '+d.style.opacity+' '+d.style.top);\n"+
			"\t\td.style.zIndex=parseFloat(d.style.zIndex)+(adz[j]*vadz);\n"+
			"\t\td.style.opacity=parseFloat(d.style.opacity)+(ado[j]*vado);\n"+
			"\t\td.style.top=parseFloat(d.style.top)+(adt[j]*vadt)+'px';\n"+
			"\t\td.style.left=parseFloat(d.style.left)+(adl[j]*vadl)+'px';\n"+
			"\t\t//console.log(d.style.zIndex+' '+d.style.opacity+' '+d.style.top);\n"+"\t}\n"+
			"}\n</script>\n"+"<script>\n\n"+
			"function initialize(inp,level){\n"+"\tvar temp=inp.childNodes;\n"+
			"\tif(level==0){\n"+"\t\tfor(var j=0;j<inp.childNodes.length;j++){\n"+
			"\t\t\tinitialize(inp.childNodes[j],1);\n"+"\t\t}\n"+"\t}\n"+
			"\telse{\n"+"\t\tfor(var j=0;j<inp.querySelectorAll('.hide').length;j++){\n"+
			"\t\t\tinp.querySelectorAll('.hide')[j].style.display='none';\n"+"\t\t}\n\t}\n}\n\n"+
			"var expander;\n"+
			"function display(){\n"+"\texpander = document.querySelectorAll('.expand');\n"+
			"\tfor (var i = 0; i < expander.length; ++i) {\n"+
			"\t\texpander[i].onclick = function(event) {\n"+"\t\t\tevent.stopPropagation();\n"+
			"\t\t\tvar pp=this.querySelectorAll('.hide');\n"+
			"\t\t\tvar firoff = this.offsetHeight;\n"+
			"\t\t\tfor(var j=0;j<this.querySelectorAll('.hide').length;j++){\n"+
			"\t\t\t\tpp[j].style.display='none';\n"+"\t\t\t}\n"+"\t\t\tif(firoff == this.offsetHeight){\n"+
			"\t\t\t\tpp=this.childNodes;\n"+"\t\t\t\tfor(var j=0;j<this.childNodes.length;j++){\n"+
			"\t\t\t\t\tpp[j].style.display='block';\n"+"\t\t\t\t}\n"+"\t\t\t}\n"+"\t\t}\n"+"\t}\n"+"}\n\n"+
			"function populate(obj,divn) {\n"+"\tconst result = [];\n"+
			"\tfor (const prop in obj) {\n"+"\t\tconst value = obj[prop];\n"+
			"\t\tif (typeof value === 'object') {\n"+"\t\t\tvar tp=document.createElement('div');\n"+
			"\t\t\tvar t=document.createElement('p');\n"+"\t\t\tt.appendChild(document.createTextNode(prop+': '));\n"+
			"\t\t\tt.className='dis';\n\t\t\ttp.appendChild(t);\n"+
			"\t\t\tvar childdivn=document.createElement('div');\n"+
			"\t\t\tchilddivn.className='expand';\n"+"\t\t\ttp.appendChild(childdivn);\n"+
			"\t\t\ttp.className='hide';\n"+"\t\t\tdivn.appendChild(tp);\n"+
			"\t\t\tpopulate(value,childdivn);\n"+"\t\t}\n"+"\t\telse {\n"+
			"\t\t\tvar t=document.createElement('p');\n"+
			"\t\t\tt.appendChild(document.createTextNode(prop+': '+value));\n"+
			"\t\t\tt.className='dis';\n"+"\t\t\tdivn.appendChild(t);\n"+"\t\t}\n"+"\t}\n"+
			"\treturn result;\n"+"}\n\n"+
			"var data='");
	bw.write(name+"';\n"+"var json = JSON.parse(data);//$.parseJSON(data);\n"+
			"populate(json,document.getElementById('maindiv'));\n"+
			"initialize(document.getElementById('maindiv'),0);\n"+"display();\n"+
			"</script>\n"+"</body>\n"+"</html>\n");
	bw.close();
	try{
		Runtime rt=Runtime.getRuntime();
		String url = sourcefile;
		File htmlFile = new File(url);
		Desktop.getDesktop().browse(htmlFile.toURI());
		//String browser = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe ";
		//Process pc = rt.exec(browser + url);
		//pc.waitFor();
	}catch(Exception e){
		System.out.println(e);
	}
	System.exit(0);
}

}
