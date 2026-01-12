FROM python:3.11-slim

WORKDIR /agent

RUN pip install psutil requests

COPY agent.py agent.py

CMD ["python","agent.py"]
