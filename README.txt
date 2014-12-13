===============================================
Hyperion Gray LLC PunkCRACK Usage Documentation
===============================================

Background:

PunkCRACK is a program for distributed password cracking over a Hadoop cluster. Please use it responsibly. If you have
any additional questions or want to know more, please feel free to visit our website at http://www.hyperiongray.com/

Assumptions:
    - You're running this on the master node of a Hadoop cluster
    - You have a password hash that you want to crack
    
Usage:

$HADOOP_HOME/bin/hadoop <PunkCRACK jar path> com.hyperiongray.punkscan.MRexample -basedir <path in HDFS to save results>\
 -charsetcode <charsetcode value> -minpasswordlenght <lower bound on the password length>\
 -maxpasswordlenght <upper bound on the password length> -encryptiontype <hash type>\
 -hash <hash to crack>
 
Most of the above are self explanatory. The charsetcode is a 4-digit code of 0s and 1s that tell PunkCRACK the character set to try against
passwords. A one means include and a 0 means do not include. The first position is lowercase letters, the second is uppercase letters, 
the third is numbers, and the fourth is special characters. So if you'd like to crack a password with just lowercase letters and numbers 
you would enter 1010. If you'd like to crack a password with lowercase, uppercase, and special chars, it would be 1101.

Once you run this command you will see a Hadoop job start and the hash will begin cracking.

Summary of Charsets Supported:

    lowercase: "abcdefghijklmnopqrstuvwxyz"
    uppercase: "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    digits: "0123456789"
    special characters supported: "!#$@&"

encryptiontype is simply the hash type that you'd like to crack. This can be any one of the following values:
    -md2
    -md5
    -sha-1
    -sha-256
    -sha-384
    -sha-512

We are continuing to add more support for hash types. Most notably, NTLM support is currently underway.

Example:

$HADOOP_HOME/bin/hadoop jar /home/pgotsr/punkcrack/punkcrack-0.0.5.jar\
 com.hyperiongray.punkscan.MRexample -basedir /home/pgotsr/punkcrack/data/ -charsetcode 1000\
 -minpasswordlenght 8 -maxpasswordlenght 8 -encryptiontype md5 -hash 5F4DCC3B5AA765D61D8327DEB882CF99

Help:

If you need additional help or find this document confusing please feel free to contact us at contact@hyperiongray.com. You'll find us
helpful and responsive!

Thanks,

The PunkCRACK Team/Hyperion Gray
