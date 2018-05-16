FROM ubuntu:16.04

COPY .docker/ /
ADD https://download.litecoin.org/litecoin-0.15.1/linux/litecoin-0.15.1-x86_64-linux-gnu.tar.gz /tmp/

RUN tar -xvf /tmp/litecoin-*.tar.gz -C /tmp/ \
 && cp /tmp/litecoin-*/bin/*  /usr/local/bin \
 && rm -rf /tmp/litecoin-*

RUN chmod a+x /usr/local/bin/*

RUN groupadd --gid 1000 litecoin \
 && useradd --uid 1000 --gid litecoin --shell /bin/false --create-home litecoin \
 && chmod +x /usr/local/bin/entrypoint

USER litecoin
WORKDIR /home/litecoin

RUN mkdir -p /home/litecoin/.litecoin

CMD ["litecoind", "-printtoconsole"]