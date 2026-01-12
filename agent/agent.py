import psutil
import requests
import time

API_URL = "http://localhost:8080/api/metrics"

while True:
    cpu = psutil.cpu_percent()
    ram = psutil.virtual_memory().percent
    disk = psutil.disk_usage('/').percent

    data = {
        "cpuUsage": cpu,
        "ramUsage": ram,
        "diskUsage": disk
    }

    try:
        requests.post(API_URL, json=data)
        print("Sent:", data)
    except:
        print("Server not reachable")

    time.sleep(10)
