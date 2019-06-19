import os

allfiles=[]
def print_files(path):
	global allfiles
	lsdir = os.listdir(path)
	dirs = [i for i in lsdir if os.path.isdir(os.path.join(path,i))]
	for d in dirs:
		print_files(os.path.join(path,d))
	files = [i for i in lsdir if os.path.isfile(os.path.join(path,i))]
	for f in files:
		allfiles+=[os.path.join(path,f)]

print_files('src')
print_files('web')
w=open('code.txt','w',encoding='utf8')

for i in allfiles:
	if i.split('\\')[-1] in ['font.woff2','icon.ico','jquery-min.js']:
		continue
	j=i.replace('\\','/')
	w.write('---------- BEGIN '+j+' ----------\n')
	f=open(i,'r',encoding='utf8')
	w.write(f.read())
	f.close()
	w.write('\n---------- END '+j+' ----------\n\n')

w.flush()
w.close()
input('Finished!')
