@echo off

RD /S /Q "C:\kafka\logs"
RD /S /Q "C:\klogs"
RD /S /Q "C:\tmp"


cd C:\kafka

start cmd.exe /k bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties

timeout 5

start cmd.exe /k "C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties"