-- Pretty debug function
function vDebugPrint(strDebugMessage)
    print(strDebugMessage)
end

-- Switches output functions 
-- This set of functions manipulate the output of the switches

-- Init the port for the switches
function vInitSwitchesPorts()
    -- set output to the relees
    vDebugPrint( "Initializare porturi 0, 1")
    gpio.mode(0, gpio.OUTPUT)
    gpio.mode(1, gpio.OUTPUT)
    gpio.mode(6, gpio.OUTPUT)
    gpio.mode(5, gpio.OUTPUT)
end

-- Turn on or off the switch
function vSetSwitchState(u8Ch,u8State)
    if u8State < 0 or u8State >1 then u8State = 0; end
    if(u8Ch == 0) then 
    -- CH0 is connected to GPIO5 -> ch 1 in the nodemcu
        if (u8State == 1) then 
             vDebugPrint("Port 1 - HIGH")
             gpio.write(1, gpio.HIGH)
        else 
             vDebugPrint("Port 1 - LOW")
             gpio.write(1, gpio.LOW) 
        end
    end
    if(u8Ch == 1) then 
    -- CH1 is connected to GPIO16 -> ch 0 in the nodemcu
        if (u8State == 1) then 
             vDebugPrint("Port 0 - HIGH")
             gpio.write(0, gpio.HIGH)
        else 
             vDebugPrint("Port 0 - LOW")
             gpio.write(0, gpio.LOW) 
        end
    end
    if(u8Ch == 2) then 
    -- CH3 is connected to GPIO14 -> ch 5 in the nodemcu
        if (u8State == 1) then 
             vDebugPrint("Port 5 - HIGH")
             gpio.write(5, gpio.HIGH)
        else 
             vDebugPrint("Port 5 - LOW")
             gpio.write(5, gpio.LOW) 
        end
    end
    if(u8Ch == 3) then 
    -- CH3 is connected to GPIO12 -> ch 6 in the nodemcu
        if (u8State == 1) then 
             vDebugPrint("Port 6 - HIGH")
             gpio.write(6, gpio.HIGH)
        else 
             vDebugPrint("Port 6 - LOW")
             gpio.write(6, gpio.LOW) 
        end
    end
end 

-- TCP IP functions
function vConnectToAccessPoint()
    ip = wifi.sta.getip()
    vDebugPrint(ip)
    --nil
    if ip == nil then
        wifi.setmode(wifi.STATION)
        wifi.sta.config("Tinel_home_2","[reteaualuitinel]")
    end
    --wifi.sta.connect()
    vDebugPrint("Getting ip")
    ip = wifi.sta.getip()
    cnt = 0
    while (ip == nil) do
        tmr.delay(500000)    
        ip = wifi.sta.getip()
        vDebugPrint("Waiting for ip " .. tostring(wifi.sta.status()) .. " " .. tostring(cnt))
        cnt = cnt + 1
        
        if cnt == 20 then
            ip = -1
        end
    end
    vDebugPrint("IP alocat de catre DHCP "..tostring(ip))
end

-- mttq server
function vMqttConnectToTopic()
    -- subscribe topic with qos = 0
    m:subscribe("/home/1/dev1/s4/com",0, function(conn) vDebugPrint("subscribe success " .. tostring(conn)) end)
    m:subscribe("/home/1/dev1/s3/com",0, function(conn) vDebugPrint("subscribe success " .. tostring(conn)) end)
    m:subscribe("/home/1/dev1/s2/com",0, function(conn) vDebugPrint("subscribe success " .. tostring(conn)) end)
    m:subscribe("/home/1/dev1/s1/com",0, function(conn) vDebugPrint("subscribe success " .. tostring(conn)) end)
end

function vMqttPublishResult()
    -- publish a message with data = hello, QoS = 0, retain = 0
    m:publish("/home/1/dev1/s2/state","ON",0,0, function(conn) vDebugPrint("sent " .. tostring(conn)) end)
end

function vMqttConnectToServer()
    -- init mqtt client with keepalive timer 120sec
    m = mqtt.Client("Device1", 120, "", "")
    
    -- setup Last Will and Testament (optional)
    -- Broker will publish a message with qos = 0, retain = 0, data = "offline" 
    -- to topic "/lwt" if client don't send keepalive packet
    m:lwt("/lwt", "offline", 0, 0)
    m:on("connect", function(con) vDebugPrint("connected") end)
    m:on("offline", function(con) vDebugPrint("offline") vMqttConnectToServer() end)
    
   --  on publish message receive event
    m:on("message", function(conn, topic, data) 
                               vDebugPrint(topic .. ":" ) 
                                 if data ~= nil then
                                    vDebugPrint("Message data " .. data)
                                    -- first button
                                    if topic == "/home/1/dev1/s1/com" then
                                        vDebugPrint("s1 switch message detected")
                                        if data == "0" then
                                            vDebugPrint("Set pin 0 to 0")
                                            vSetSwitchState(0,0)
                                        end
                                        if data == "1" then
                                            vDebugPrint("Set pin 0 to 1")
                                            vSetSwitchState(0,1)
                                        end
                                    end
                                    -- second button
                                    if topic == "/home/1/dev1/s2/com" then
                                        vDebugPrint("s2 switch message detected")
                                        if data == "0" then
                                            vDebugPrint("Set pin 1 to 0")
                                            vSetSwitchState(1,0)
                                        end
                                        if data == "1" then
                                            vDebugPrint("Set pin 1 to 1")
                                            vSetSwitchState(1,1)
                                        end
                                    end
                                    -- third button
                                    if topic == "/home/1/dev1/s3/com" then
                                        vDebugPrint("s3 switch message detected")
                                        if data == "0" then
                                            vDebugPrint("Set pin 2 to 0")
                                            vSetSwitchState(2,0)
                                        end
                                        if data == "1" then
                                            vDebugPrint("Set pin 2 to 1")
                                            vSetSwitchState(2,1)
                                        end
                                    end
                                    -- forth button
                                    if topic == "/home/1/dev1/s4/com" then
                                        vDebugPrint("s3 switch message detected")
                                        if data == "0" then
                                            vDebugPrint("Set pin 3 to 0")
                                            vSetSwitchState(3,0)
                                        end
                                        if data == "1" then
                                            vDebugPrint("Set pin 3 to 1")
                                            vSetSwitchState(3,1)
                                        end
                                    end
                                    
                                    
                                  end
                            end
        )
    
    -- for secure: m:connect("192.168.11.118", 1880, 1)
    m:connect("tinel.go.ro", 1883, 0, function(conn) vDebugPrint("connected " .. tostring(conn)) vMqttConnectToTopic() end)
    vDebugPrint("Connect to the broker")
        
    --m:close();
    -- you can call m:connect again

end

vInitSwitchesPorts()
vConnectToAccessPoint()
tmr.delay(100000)
vMqttConnectToServer()
