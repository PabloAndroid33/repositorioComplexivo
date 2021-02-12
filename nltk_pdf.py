import socket
import fitz
import nltk
import string
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from collections import Counter
from _collections import OrderedDict
from nltk.corpus import stopwords
nltk.download('punkt')






HOST = "localhost"
PORT = 3035
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('Socket created')
s.bind((HOST, PORT))
s.listen()
print("Socket Listening")
conn, addr = s.accept()


conn.send(bytes("Message"+"\r\n",'UTF-8'))
print("Message sent")
data = conn.recv(1024)
urlcadena=data.decode(encoding='UTF-8')



def sube(nombrearchivo):
	
	doc=fitz.open(nombrearchivo)
	salida=open(nombrearchivo+".txt","wb")
	for pagina in doc:
		texto=pagina.getText().encode("utf8")
		salida.write(texto)
		salida.write(b"\n-----\n")
	salida.close()

	with open(nombrearchivo+'.txt','r',encoding='UTF8') as archivo:
		texto = archivo.read()
	

	


	

	stop_words = set(stopwords.words('spanish'))

	word_tokens=word_tokenize(texto)


     
	word_tokens=list(filter(lambda token: token not in string.punctuation,word_tokens))

#areglo=[]
	#word_tokens.append("--")
	filtro=[]

	for palabra in word_tokens:
		if palabra not in stop_words:
			filtro.append(palabra)


	
	c=Counter(filtro)

	y=OrderedDict(c.most_common())
	with open(nombrearchivo+'KEYWORDS.txt','w',encoding='UTF8') as far:
		for k,v in y.items():
			far.write(f"{k} {v}\n")




#sube("C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/3 4G tecnologias.pdf");
sube(urlcadena);
conn.close() 